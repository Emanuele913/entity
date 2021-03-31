package response.entity.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ruolo")
@Data
public class Ruolo {

    @Id
    @Column(name = "nome_ruolo")
    private String nome_ruolo;

    @OneToOne(mappedBy = "ruolo", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Dipendente dipendente;
}
