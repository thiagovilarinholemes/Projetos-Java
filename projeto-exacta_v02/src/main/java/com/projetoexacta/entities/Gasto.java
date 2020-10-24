package com.projetoexacta.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "tb_gasto")
public class Gasto implements Serializable {

    /** Columns Database */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private Calendar dataHora;
    private Double valor;

    /** 1 * relationship with the Tag table */
    @OneToMany(mappedBy = "gasto")
    private Set<Tag> tags = new HashSet<>();

    /** Constructs */
    public Gasto() {
    }

    public Gasto(Long id, String nome, String descricao, Calendar dataHora, Double valor) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.valor = valor;
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

    public void setNome(String nomePessoa) {
        this.nome = nomePessoa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Calendar getDataHora() {
        return dataHora;
    }

    public void setDataHora(Calendar dataHora) {
        this.dataHora = dataHora; //Calendar.getInstance();
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set tags) {
        this.tags = tags;
    }


    /** equals and hashCode */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gasto gasto = (Gasto) o;
        return Objects.equals(id, gasto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
