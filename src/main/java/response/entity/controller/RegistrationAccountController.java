package response.entity.controller;

import com.sun.corba.se.spi.ior.ObjectKey;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import response.entity.model.AccountDipendente;
import response.entity.model.Dipendente;
import response.entity.model.form.CheckIdentityForm;
import response.entity.model.form.RegistrationAccountForm;
import response.entity.service.AccountDipendenteService;
import response.entity.service.DipendenteService;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Date;

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

        if (bindingResult.hasErrors()) {
            log.error("{}", bindingResult);
            return "registration_account";
        }

        if (!bindingResult.hasErrors()) {
            Dipendente dipendente = dipendenteService.checkDipendente(checkIdentityForm.getCf_conferma(), checkIdentityForm.getEmail_conferma());
            log.info("Dipendente trovato : {}", dipendente);

            if (dipendente.getAccountDipendente() != null) {
                model.addAttribute("isRegistered", true);
            }
            if (dipendente == null) {
                model.addAttribute("isNull", true);
            }
            if (dipendente != null && dipendente.getAccountDipendente() == null) {
                session.setAttribute("Dipendente", dipendente);
                session.setAttribute("email", dipendente.getEmail());
                session.setAttribute("codice_fiscale", dipendente.getCodice_fiscale());
            }
        }

        return "registration_account";
    }

    @PostMapping("/regAccount")
    public String registerAccount(@Valid RegistrationAccountForm registrationAccountForm,
                                  BindingResult bindingResult, CheckIdentityForm checkIdentityForm,
                                  HttpSession session) {

        if (bindingResult.hasErrors()) {
            log.error("{}", bindingResult);
            return "registration_account";
        }
        if (!bindingResult.hasErrors()) {
            AccountDipendente accountDipendente = new AccountDipendente(registrationAccountForm, (String) session.getAttribute("email"));

            Dipendente dipendente = (Dipendente) session.getAttribute("Dipendente");

            accountDipendente.setData_registrazione(LocalDate.now());
            accountDipendenteService.insertAccountDipendente(accountDipendente);

            dipendente.setAccountDipendente(accountDipendente);
            dipendenteService.updateDipendenteAccount(accountDipendente,
                    (String) session.getAttribute("codice_fiscale"),
                    (String) session.getAttribute("email"));


        }

        return "registration_account";
    }

}
