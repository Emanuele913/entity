package response.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import response.entity.model.User;


public interface AccountDipendenteRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    @Query("Select a.password from User a where a.username=:username")
    String getPasswordbyUsername(@Param("username") String username);

    boolean existsAccountDipendenteByUsername(String username);

}
