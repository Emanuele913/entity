package response.entity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import response.entity.model.AccountDipendente;
import response.entity.service.AccountDipendenteService;


@Service
public class MyUserDetailsService implements UserDetailsService {

    private final AccountDipendenteService accountDipendenteService;

    @Autowired
    private MyUserDetailsService(AccountDipendenteService accountDipendenteService){
        this.accountDipendenteService = accountDipendenteService;
    }

/*    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return new User("foo", "foo",
                new ArrayList<>());
    }*/

   @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AccountDipendente user = accountDipendenteService.findByUserName(userName);

       return new MyUserDetails(user);
    }
}
