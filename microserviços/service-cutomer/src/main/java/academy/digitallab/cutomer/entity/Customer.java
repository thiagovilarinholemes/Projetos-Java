package academy.digitallab.cutomer.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name="tbl_customers")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Este campo não pode ser vazio")
    @Size( min = 8 , max = 8, message = "O tamanho deste campo é 8 caracters") // Tamanho do campo, delimita o campo
    @Column(name = "number_id" , unique = true ,length = 8, nullable = false) // nome da coluna | valor único | tamanho 8 | não pode ser null
    private String numberID;

    @NotEmpty(message = "Este campo não pode ser vazio")
    @Column(name="first_name", nullable=false)
    private String firstName;

    @NotEmpty(message = "Este campo não pode ser vazio")
    @Column(name="last_name", nullable=false)
    private String lastName;

    @NotEmpty(message = "Este campo não pode ser vazio")
    @Email(message = "Formato incorreto de email") // Formata o campo para email
    @Column(unique=true, nullable=false)
    private String email;

    @Column(name="photo_url")
    private String photoUrl;


    @NotNull(message = "Este campo não pode ser vazio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Region region;

    private String state;
}
