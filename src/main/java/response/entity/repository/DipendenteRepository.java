package response.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import response.entity.model.AccountDipendente;
import response.entity.model.Dipendente;

import java.util.List;
import java.util.Optional;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, Integer> {

    Dipendente findById(int id);

    @Query(value = "SELECT u FROM Dipendente u")
    List<Dipendente> getAllUsers();

    @Query("SELECT d FROM Dipendente d WHERE d.codice_fiscale=:codice_fiscale and d.email=:email")
    Dipendente checkDipendente(@Param("codice_fiscale") String codice_fiscale, @Param("email") String email);

    @Query("select d from Dipendente d where d.codice_fiscale = :#{#req. codice_fiscale} and d.email = :#{#req.email}")
    Optional<Dipendente> findQuestionDetails(@Param("req") Dipendente req);


}
