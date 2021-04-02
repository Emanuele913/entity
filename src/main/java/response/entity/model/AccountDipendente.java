package response.entity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import response.entity.model.form.RegistrationAccountForm;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "account_dipendente")
@Data
@NoArgsConstructor
public class AccountDipendente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_account")
    private int id_account;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "data_registrazione")
    private LocalDate data_registrazione;


    @OneToOne(mappedBy = "accountDipendente", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Dipendente dipendente;

    public AccountDipendente(RegistrationAccountForm registrationAccountForm,String email) {
        this.username = registrationAccountForm.getUsername();
        this.password = registrationAccountForm.getPassword();
        this.email = email;
    }
}
