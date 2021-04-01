package response.entity.controller;

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
import response.entity.model.Dipendente;
import response.entity.model.form.CheckIdentityForm;
import response.entity.model.form.RegistrationAccountForm;
import response.entity.service.DipendenteService;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
@Slf4j
public class RegistrationAccountController {

    private final DipendenteService dipendenteService;

    @Autowired
    private RegistrationAccountController(DipendenteService dipendenteService) {
        this.dipendenteService = dipendenteService;
    }

    @GetMapping("/indexAccount")
    public String accountHome() {

        return "registration_account";
    }

    @PostMapping("checkIdentity")
    public String checkIdentity(@Valid CheckIdentityForm checkIdentityForm,
                                BindingResult bindingResult, RegistrationAccountForm registrationAccountForm,
                                Model model) {

        if (bindingResult.hasErrors()) {
            log.error("{}", bindingResult);
            return "registration_account";
        }

        if (!bindingResult.hasErrors()) {
            Dipendente dipendente = dipendenteService.checkDipendente(checkIdentityForm.getCf_conferma(), checkIdentityForm.getEmail_conferma());
            log.info("Dipendente trovato : {}", dipendente);
            if (dipendente == null){
               model.addAttribute("genericError","blabla");
            }
        }

        return "registration_account";
    }

    @PostMapping("regAccount")
    public String registerAccount(@Valid RegistrationAccountForm registrationAccountForm,
                                  BindingResult bindingResult, CheckIdentityForm checkIdentityForm) {

        return "registration_account";
    }

}
