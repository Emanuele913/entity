package response.entity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import response.entity.model.Dipendente;
import response.entity.model.form.RegistrationDipendenteForm;
import response.entity.service.DipendenteService;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
@Slf4j
public class RegistrationDipendenteController {

    private final DipendenteService dipendenteService;

    @Autowired
    private RegistrationDipendenteController(DipendenteService dipendenteService) {
        this.dipendenteService = dipendenteService;
    }

    @GetMapping("/indexDependent")
    public String showForm(RegistrationDipendenteForm registrationDipendenteForm) {
        return "registration_dipendente";
    }

    @PostMapping("/regDependent")
    public String checkPersonInfo(@Valid RegistrationDipendenteForm registrationDipendenteForm,
                                  BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            log.error("{}",bindingResult);
            return "registration_dipendente";
        }

        if (!bindingResult.hasErrors()){
            Dipendente dipendente = new Dipendente(registrationDipendenteForm);
            boolean isSaved = dipendenteService.insertDipendente(dipendente);

            if (!isSaved){
                model.addAttribute("isPresent",true);
                return "registration_dipendente";
            }

            model.addAttribute("registrationComplete",true);
            RegistrationDipendenteForm registrationDipendenteReset = new RegistrationDipendenteForm();
            model.addAttribute("registrationDipendenteForm",registrationDipendenteReset);
        }

        return "registration_dipendente";
    }

}
