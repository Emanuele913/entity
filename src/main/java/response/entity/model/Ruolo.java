package response.entity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ruolo")
@Data
public class Ruolo {

    @Id
    @Column(name = "id_ruolo")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_ruolo;

    @Column(name = "nome_ruolo")
    private String nome_ruolo;

    @JsonBackReference
    @OneToMany(mappedBy = "ruolo", cascade = CascadeType.ALL)
    private List<Dipendente> dipendenti;
}
