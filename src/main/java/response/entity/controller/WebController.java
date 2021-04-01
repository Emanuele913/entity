package response.entity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import response.entity.model.Azienda;
import response.entity.model.Dipendente;
import response.entity.model.form.RegistrationAccountForm;
import response.entity.model.form.RegistrationDipendenteForm;
import response.entity.service.AziendaService;
import response.entity.service.DipendenteService;
import response.entity.service.RuoloService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class WebController {

    private final DipendenteService dipendenteService;
    private final AziendaService aziendaService;
    private final RuoloService ruoloService;

    @Autowired
    private WebController(DipendenteService dipendenteService,AziendaService aziendaService,RuoloService ruoloService) {
        this.dipendenteService = dipendenteService;
        this.aziendaService = aziendaService;
        this.ruoloService = ruoloService;
    }

    @GetMapping("/index")
    public String showForm(RegistrationDipendenteForm registrationDipendenteForm,Model model) {
        model.addAttribute("listAziende", aziendaService.getAllAziende());
        model.addAttribute("listRuoli",ruoloService.getAllRuoli());
        return "home";
    }

    @PostMapping("/index/registrazioneDipendente")
    public String checkPersonInfo(@Valid RegistrationDipendenteForm registrationDipendenteForm,
                                  BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "home";
        }

        if (!bindingResult.hasErrors()){

            Dipendente dipendente = new Dipendente(registrationDipendenteForm);
            dipendente.setAzienda(aziendaService.findAzienda(registrationDipendenteForm.getNome_azienda()));
            dipendenteService.insertDipendente(dipendente);
        }

        return "results";
    }

}
