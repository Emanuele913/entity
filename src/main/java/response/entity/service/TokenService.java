package response.entity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import response.entity.model.AccountDipendente;
import response.entity.model.Token;
import response.entity.repository.RuoloRepository;
import response.entity.repository.TokenRepository;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    @Autowired
    private TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }


    public void saveToken(Token jwt) {
        tokenRepository.save(jwt);
    }

    public String getRefreshToken(AccountDipendente accountDipendente) {
        return tokenRepository.findTokenByRefreshToken(accountDipendente);
    }

    public String getAccessToken(AccountDipendente accountDipendente) {
        return tokenRepository.findTokenByAccessToken(accountDipendente);
    }
}
