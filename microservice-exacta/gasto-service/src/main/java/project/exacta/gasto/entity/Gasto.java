package project.exacta.gasto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_gasto")
public class Gasto implements Serializable{

    /** Columns Database */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Este campo não pode ser vazio")
    @Column(name = "nome")
    private String name;

    @NotEmpty(message = "Este campo não pode ser vazio")
    @Column(name = "descricao")
    private String description;

    @Column(name = "dataHora")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar date;

    @Column(name = "valor")
    private Double valor;

    /** 1 * relationship with the Tag table */
    @OneToMany(mappedBy = "gasto")
    private List<Tag> tags = new ArrayList<>();

    @Column(name = "status")
    private String status;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    /** Construct **/
    public Gasto(Long id, String name, String description, Double valor) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.valor = valor;
    }

}
