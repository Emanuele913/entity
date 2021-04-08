package response.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import response.entity.model.AccountDipendente;
import response.entity.model.Token;

public interface TokenRepository extends JpaRepository<Token, Integer> {


/*    @Query("Select ad.username From Token t JOIN AccountDipendente ad on ad.id_account = t.id_account where t.access_token=: access_token")
    Token getAccountNameByAccessToken(@Param("access_token") String access_token);*/


    @Query("select a.refresh_token from Token a where a.accountDipendente=:accountDipendente")
    String findTokenByRefreshToken(@Param("accountDipendente") AccountDipendente accountDipendente);

    @Query("select a.access_token from Token a where a.accountDipendente=:accountDipendente")
    String findTokenByAccessToken(@Param("accountDipendente") AccountDipendente accountDipendente);


}
