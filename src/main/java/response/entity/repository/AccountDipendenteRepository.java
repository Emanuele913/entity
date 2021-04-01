package response.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import response.entity.model.AccountDipendente;

import javax.websocket.server.PathParam;


public interface AccountDipendenteRepository extends JpaRepository<AccountDipendente, Integer> {

    @Query("Select a.password from AccountDipendente a where a.username=:username")
    String getPasswordbyUsername(@PathParam("username") String username);

    boolean existsAccountDipendenteByUsername(String username);

}
