package response.entity.service;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.NonUniqueResultException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Component;
import response.entity.model.Dipendente;
import org.springframework.beans.factory.annotation.Autowired;
import response.entity.repository.DipendenteRepository;

import java.util.List;

@Component
@Slf4j
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

    public Dipendente checkDipendente(String codice_fiscale,String email){
        Dipendente dipendente = null;
        try{
           dipendente = dipendenteRepository.checkDipendente(codice_fiscale,email);
        }  catch (IncorrectResultSizeDataAccessException e) {
        log.error("Per questi dati corrispondono più utenti");
    }
        return dipendente;
    }

}
