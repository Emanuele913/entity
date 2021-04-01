package response.entity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ruolo")
@Data
public class Ruolo {

    @Id
    @Column(name = "nome_ruolo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String nome_ruolo;

    @JsonBackReference
    @OneToMany(mappedBy = "ruolo", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private List<Dipendente> dipendenti;
}
