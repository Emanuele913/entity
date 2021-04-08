package response.entity.model;

import lombok.Data;
import response.entity.model.form.RegistrationAccountForm;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "USERS")
@Data
public class User {


    @Id
    @Column(name = "USERNAME", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String username;

    @Column
    @NotNull
    private String email;

    @Column
    @NotNull
    private LocalDate data_registrazione;

    @Column(name = "PASSWORD", length = 100)
    @NotNull
    @Size(min = 4, max = 100)
    private String password;

    @Column(name = "ENABLED")
    @NotNull
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USERS_AUTHORITIES",
            joinColumns = {@JoinColumn(name = "USER_USERNAME", referencedColumnName = "USERNAME")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
    private List<Authority> authorities;


    public User(RegistrationAccountForm registrationAccountForm,String email){
        this.username = registrationAccountForm.getUsername();
        this.password = registrationAccountForm.getPassword();
        this.enabled = true;
        this.email = email;
    }


}