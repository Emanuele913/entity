package response.entity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import response.entity.model.Dipendente;
import response.entity.model.form.RegistrationAccountForm;
import response.entity.model.form.RegistrationDipendenteForm;
import response.entity.service.DipendenteService;

import java.util.List;

@Controller
public class DipendenteController {

    private final DipendenteService service;

    @Autowired
    private DipendenteController(DipendenteService dipendenteService) {
        this.service = dipendenteService;
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<Dipendente>> getAllUsers() {
        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/5emezza")
    ResponseEntity<List<Dipendente>> customHeader() {
        return ResponseEntity.ok().header("Custom-Header", "foo").body(service.getAllUsers());
    }
}
