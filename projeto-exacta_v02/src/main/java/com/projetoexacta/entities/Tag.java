package com.projetoexacta.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_tag")
public class Tag implements Serializable {

    /** Variables */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    /** * 1 relationship with the Gasto table */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "gasto_id")
    private Gasto gasto;


    /** Constructs */
    public Tag(){}

    public Tag(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Tag(Long id, String nome, Gasto gasto) {
        this.id = id;
        this.nome = nome;
        this.gasto = gasto;
    }


    /** Getters and Setters */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Gasto getGasto() {
        return gasto;
    }

    public void setGasto(Gasto gasto) {
        this.gasto = gasto;
    }


    /** Equals and HashCode */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
