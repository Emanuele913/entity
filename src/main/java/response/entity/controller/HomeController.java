package response.entity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import response.entity.model.form.LoginForm;
import response.entity.service.AccountDipendenteService;

import javax.validation.Valid;

@Slf4j
@Controller
public class HomeController {

    private final AccountDipendenteService accountDipendenteService;

    @Autowired
    private HomeController(AccountDipendenteService accountDipendenteService) {
        this.accountDipendenteService = accountDipendenteService;
    }

    @GetMapping("/home")
    public String home(LoginForm loginForm) {

        return "home";
    }

    //Per generare la password encriptata a partire dalla pass in chiaro
    //String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));

    @PostMapping("/login")
    public String login(@Valid LoginForm loginForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            log.error("{}", bindingResult);
            return "home";
        }

        if (accountDipendenteService.existsUserByUsername(loginForm.getUsername()) &&
                BCrypt.checkpw(loginForm.getPassword(), accountDipendenteService.getPasswordbyUsername(loginForm.getUsername()))) {
            return "registration_account";
        }
        model.addAttribute("error", "Username o Password errati");

        return "home";

    }
}
