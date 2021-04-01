package response.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import response.entity.model.Azienda;

public interface AziendaRepository extends JpaRepository<Azienda, Integer> {

    @Query("SELECT a FROM Azienda a WHERE a.nome_azienda= :nome")
    Azienda findAzienda(@Param("nome") String nome);
}
