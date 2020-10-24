package project.exacta.gasto.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_tag")
public class Tag implements Serializable{

    /** Columns Database */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String name;

    /** * 1 relationship with the Gasto table */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "gasto_id")
    private Gasto gasto;

    /** Construct **/
    public Tag(Long id, String name){
        this.id = id;
        this.name = name;
    }
}
