package response.entity.service;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.NonUniqueResultException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import response.entity.model.AccountDipendente;
import response.entity.model.Dipendente;
import org.springframework.beans.factory.annotation.Autowired;
import response.entity.repository.DipendenteRepository;

import java.util.List;
import java.util.Optional;

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

    public Dipendente checkDipendente(String codice_fiscale, String email) {
        Dipendente dipendente = null;
        try {
            dipendente = dipendenteRepository.checkDipendente(codice_fiscale, email);
        } catch (IncorrectResultSizeDataAccessException e) {
            log.error("Per questi dati corrispondono più utenti");
        }
        return dipendente;
    }

    public void updateDipendenteAccount(AccountDipendente accountDipendente, String codice_fiscale, String email) {

        Dipendente dipendente = checkDipendente(codice_fiscale, email);
        dipendente.setAccountDipendente(accountDipendente);
        dipendenteRepository.save(dipendente);
    }

    public boolean insertDipendente(Dipendente dipendente) {

        if (dipendenteRepository.findQuestionDetails(dipendente).isPresent()) {
            log.warn("Dipendente già presente");
            return false;
        }

        log.info("Dipendente inserito con successo : {} ", dipendenteRepository.save(dipendente));
        return true;

    }
}
