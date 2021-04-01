package response.entity.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import response.entity.model.Dipendente;
import org.springframework.beans.factory.annotation.Autowired;
import response.entity.model.form.RegistrationDipendenteForm;
import response.entity.repository.DipendenteRepository;

import java.util.List;

@Component
public class DipendenteService {

    private final DipendenteRepository dipendenteRepository;

    @Autowired
    public DipendenteService(DipendenteRepository dipendenteRepository) {
        this.dipendenteRepository = dipendenteRepository;
    }


    public Dipendente findUserById(int id) {

        return dipendenteRepository.findById(id);
    }

    public List<Dipendente> getAllUsers() {
        return dipendenteRepository.getAllUsers();
    }

    public void insertDipendente(Dipendente dipendente){

        dipendenteRepository.save(dipendente);
    }

}
