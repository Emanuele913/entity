package response.entity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "account_dipendente")
@Data
public class AccountDipendente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_account")
    private int id_account;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "data_registrazione")
    private Date data_registrazione;

    @JsonBackReference
    @OneToOne(mappedBy = "accountDipendente", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Dipendente dipendente;


}
