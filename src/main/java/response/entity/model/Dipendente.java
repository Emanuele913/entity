package response.entity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import response.entity.model.form.RegistrationDipendenteForm;

import javax.persistence.*;

@Data
@Entity
@Table(name = "dipendente")
@NoArgsConstructor
@AllArgsConstructor
public class Dipendente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_dipendente")
    private int id_dipendente;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "codice_fiscale", unique = true)
    private String codice_fiscale;

    @Column(name = "email" , unique = true)
    private String email;

    @Column(name = "numero_telefono")
    private String numero_telefono;

    @Column(name = "salario")
    private String salario;

    @JsonManagedReference
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="id_azienda")
    private Azienda azienda;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nome_ruolo", referencedColumnName = "nome_ruolo")
    private Ruolo ruolo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_account", referencedColumnName = "id_account")
    private AccountDipendente accountDipendente;


    public Dipendente(RegistrationDipendenteForm registrationDipendenteForm){
        this.nome = registrationDipendenteForm.getNome();
        this.cognome = registrationDipendenteForm.getCognome();
        this.codice_fiscale = registrationDipendenteForm.getCodice_fiscale();
        this.email = registrationDipendenteForm.getEmail();
        this.numero_telefono = registrationDipendenteForm.getNumero_telefono();
    }
}
