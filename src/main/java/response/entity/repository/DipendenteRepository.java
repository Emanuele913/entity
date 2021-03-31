package response.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import response.entity.model.Dipendente;

import java.util.List;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, Integer> {

    Dipendente findById(int id);

    @Query(value = "SELECT u FROM Dipendente u")
    List<Dipendente> getAllUsers();
}
