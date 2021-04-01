package response.entity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import response.entity.model.Dipendente;
import response.entity.model.form.LoginForm;
import response.entity.model.form.RegistrationDipendenteForm;
import response.entity.service.AccountDipendenteService;
import response.entity.service.AziendaService;
import response.entity.service.DipendenteService;
import response.entity.service.RuoloService;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
@Slf4j
public class RegistrationDipendenteController {

    private final DipendenteService dipendenteService;
    private final AziendaService aziendaService;
    private final RuoloService ruoloService;
    private final AccountDipendenteService accountDipendenteService;

    @Autowired
    private RegistrationDipendenteController(DipendenteService dipendenteService, AziendaService aziendaService, RuoloService ruoloService,AccountDipendenteService accountDipendenteService) {
        this.dipendenteService = dipendenteService;
        this.aziendaService = aziendaService;
        this.ruoloService = ruoloService;
        this.accountDipendenteService = accountDipendenteService;
    }

    @GetMapping("/indexDependent")
    public String showForm(RegistrationDipendenteForm registrationDipendenteForm,Model model) {
        model.addAttribute("listAziende", aziendaService.getAllAziende());
        model.addAttribute("listRuoli",ruoloService.getAllRuoli());
        return "registration_dipendente";
    }

    @PostMapping("/regDependent")
    public String checkPersonInfo(@Valid RegistrationDipendenteForm registrationDipendenteForm,
                                  BindingResult bindingResult , Model model) {
        model.addAttribute("listAziende", aziendaService.getAllAziende());
        model.addAttribute("listRuoli",ruoloService.getAllRuoli());

        if (bindingResult.hasErrors()) {
            log.error("{}",bindingResult);
            return "registration_dipendente";
        }

        if (!bindingResult.hasErrors()){

            Dipendente dipendente = new Dipendente(registrationDipendenteForm);
            dipendente.setAzienda(aziendaService.findAzienda(registrationDipendenteForm.getNome_azienda()));
            dipendente.setRuolo(ruoloService.findRuolo(registrationDipendenteForm.getNome_ruolo()));
            dipendenteService.insertDipendente(dipendente);
        }

        return "results";
    }

}
