package response.entity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import response.entity.model.Dipendente;
import response.entity.model.User;
import response.entity.model.form.CheckIdentityForm;
import response.entity.model.form.RegistrationAccountForm;
import response.entity.service.AccountDipendenteService;
import response.entity.service.DipendenteService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/registration")
@Slf4j
public class RegistrationAccountController {

    private final DipendenteService dipendenteService;
    private final AccountDipendenteService accountDipendenteService;

    @Autowired
    private RegistrationAccountController(DipendenteService dipendenteService, AccountDipendenteService accountDipendenteService) {
        this.dipendenteService = dipendenteService;
        this.accountDipendenteService = accountDipendenteService;
    }

    @GetMapping("/indexAccount")
    public String accountHome(CheckIdentityForm checkIdentityForm, RegistrationAccountForm registrationAccountForm) {

        return "registration_account";
    }

    @PostMapping("/checkIdentity")
    public String checkIdentity(@Valid CheckIdentityForm checkIdentityForm,
                                BindingResult bindingResult, RegistrationAccountForm registrationAccountForm,
                                Model model, HttpSession session) {
        model.addAttribute("disableForm1", false);
        if (bindingResult.hasErrors()) {
            log.error("{}", bindingResult);
            return "registration_account";
        }

        if (!bindingResult.hasErrors()) {
            Dipendente dipendente = dipendenteService.checkDipendente(checkIdentityForm.getCf_conferma(), checkIdentityForm.getEmail_conferma());
            log.info("Dipendente trovato : {}", dipendente);

            if (dipendente == null) {
                model.addAttribute("isNull", true);
                return "registration_account";
            }

            if (dipendente.getAccountDipendente() != null) {
                model.addAttribute("isRegistered", true);
            }

            if (dipendente.getAccountDipendente() == null) {
                session.setAttribute("Dipendente", dipendente);
                session.setAttribute("email", dipendente.getEmail());
                session.setAttribute("codice_fiscale", dipendente.getCodice_fiscale());
                session.setAttribute("nome_dipendente",dipendente.getNome());

                model.addAttribute("nome_dipendente", dipendente.getNome());
                model.addAttribute("okFirstForm", true);
                model.addAttribute("disableForm1", true);
            }
        }

        return "registration_account";
    }

    @PostMapping("/regAccount")
    public String registerAccount(@Valid RegistrationAccountForm registrationAccountForm,
                                        BindingResult bindingResult, CheckIdentityForm checkIdentityForm,
                                        HttpSession session, Model model) {
        model.addAttribute("disableForm2", false);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("registrationAccountForm",registrationAccountForm);
        if (bindingResult.hasErrors()) {
            log.error("{}", bindingResult);
            return returnErrors(checkIdentityForm, session, model);
        }

        if (!registrationAccountForm.getPassword().equals(registrationAccountForm.getConferma_password())) {
            model.addAttribute("differentPasswords", true);
            log.error("Le password non corrispondono");
            return returnErrors(checkIdentityForm, session, model);
        }
        if (!bindingResult.hasErrors()) {
            User accountDipendente = new User(registrationAccountForm, (String) session.getAttribute("email"));

            Dipendente dipendente = (Dipendente) session.getAttribute("Dipendente");

            accountDipendente.setData_registrazione(LocalDate.now());
            accountDipendenteService.insertAccountDipendente(accountDipendente);

            dipendente.setAccountDipendente(accountDipendente);
            dipendenteService.updateDipendenteAccount(accountDipendente,
                    (String) session.getAttribute("codice_fiscale"),
                    (String) session.getAttribute("email"));

            model.addAttribute("disableForm2",true);
        }
        return "success";
    }

    private String returnErrors(CheckIdentityForm checkIdentityForm, HttpSession session, Model model) {
        checkIdentityForm.setCf_conferma((String) session.getAttribute("codice_fiscale"));
        checkIdentityForm.setEmail_conferma((String) session.getAttribute("email"));
        model.addAttribute("nome_dipendente", session.getAttribute("nome_dipendente"));
        model.addAttribute("okFirstForm", true);
        model.addAttribute("disableForm1", true);
        return "registration_account";
    }

}
