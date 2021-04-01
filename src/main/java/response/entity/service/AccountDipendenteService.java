package response.entity.service;

import org.springframework.stereotype.Service;
import response.entity.repository.AccountDipendenteRepository;

@Service
public class AccountDipendenteService {

    private AccountDipendenteRepository accountDipendenteRepository;

    private AccountDipendenteService(AccountDipendenteRepository accountDipendenteRepository) {
        this.accountDipendenteRepository = accountDipendenteRepository;
    }

    public String getPasswordbyUsername(String username) {
        return accountDipendenteRepository.getPasswordbyUsername(username);
    }

    public boolean existsUserByUsername(String username) {
        return accountDipendenteRepository.existsAccountDipendenteByUsername(username);
    }
}
