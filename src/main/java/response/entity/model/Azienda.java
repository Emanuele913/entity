package response.entity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "azienda")
@Data
public class Azienda {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_azienda")
    private int id_azienda;

    @Column(name = "nome_azienda")
    private String nome_azienda;

    @Column(name = "tipo_societa")
    private String tipo_societa;

    @Column(name = "numero_dipendenti")
    private int numero_dipendenti;

    @JsonBackReference
    @OneToMany(mappedBy = "azienda",cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    private List<Dipendente> dipendenti;

}
