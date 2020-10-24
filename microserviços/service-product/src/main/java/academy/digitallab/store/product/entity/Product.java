package academy.digitallab.store.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_products")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @NotEmpty(message = "Este campo não pode ser vazio") // Campo não pode ser vazio
    private String name;

    @Positive(message = "O estoque tem que ser maior que 0") // Somente números positivos
    private Double stock;

    private Double price;
    private String status;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP) // TemporalType.DATE somente a data | TemporalType.TIME somente a hora | TemporalType.TIMESTAMP hora e data
    private Date createAt;

    @NotNull(message = "A categoria não pode ser vazia")
    @ManyToOne(fetch = FetchType.LAZY) // fetch = FetchType.EAGER carrega tudo de uma vez | fetch = FetchType.LAZY carrega somente guando necessário
    @JoinColumn(name = "category_id") // cria o mapeamento com a tabela category
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"}) // Para evitar a recursividade infinita
    private Category category;
}
