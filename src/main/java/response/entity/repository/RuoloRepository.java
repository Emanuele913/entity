package response.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import response.entity.model.Azienda;
import response.entity.model.Ruolo;

public interface RuoloRepository extends JpaRepository<Ruolo,String> {

    @Query("SELECT a FROM Ruolo a WHERE a.nome_ruolo= :nome")
    Ruolo findRuolo(@Param("nome") String nome);
}
