package response.entity.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "dipendente")
public class Dipendente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dipendente")
    private int id_dipendente;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "codice_fiscale")
    private String codice_fiscale;

    @Column(name = "email")
    private String email;

    @Column(name = "numero_telefono")
    private String numero_telefono;

    @Column(name = "salario")
    private String salario;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="id_azienda", nullable=false)
    private Azienda azienda;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nome_ruolo", referencedColumnName = "nome_ruolo")
    private Ruolo ruolo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_account", referencedColumnName = "id_account")
    private AccountDipendente accountDipendente;

}
