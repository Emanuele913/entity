package response.entity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import response.entity.model.User;
import response.entity.repository.AccountDipendenteRepository;

@Service
public class AccountDipendenteService {

    private final AccountDipendenteRepository accountDipendenteRepository;

    @Autowired
    private AccountDipendenteService(AccountDipendenteRepository accountDipendenteRepository) {
        this.accountDipendenteRepository = accountDipendenteRepository;
    }

    public String getPasswordbyUsername(String username) {
        return accountDipendenteRepository.getPasswordbyUsername(username);
    }

    public boolean existsUserByUsername(String username) {
        return accountDipendenteRepository.existsAccountDipendenteByUsername(username);
    }

    public void insertAccountDipendente(User accountDipendente){
        String hashed = BCrypt.hashpw(accountDipendente.getPassword(), BCrypt.gensalt(12));
        accountDipendente.setPassword(hashed);
        accountDipendenteRepository.save(accountDipendente);
    }
}
