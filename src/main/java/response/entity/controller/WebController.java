package response.entity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import response.entity.model.form.RegistrationAccountForm;
import response.entity.model.form.RegistrationDipendenteForm;
import response.entity.service.DipendenteService;

import javax.validation.Valid;

@Controller
public class WebController {

    private final DipendenteService service;

    @Autowired
    private WebController(DipendenteService dipendenteService) {
        this.service = dipendenteService;
    }

    @GetMapping("/index")
    public String showForm(RegistrationDipendenteForm registrationDipendenteForm) {

        return "home";
    }

    @PostMapping("/index/registrazioneDipendente")
    public String checkPersonInfo(@Valid RegistrationDipendenteForm registrationDipendenteForm,
                                  BindingResult bindingResult){

        
        System.out.println(registrationDipendenteForm.getCodice_fiscale());
        System.out.println(bindingResult);
        if (bindingResult.hasErrors()) {
            return "home";
        }

        return "results";
    }

}
