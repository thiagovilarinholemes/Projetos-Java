package com.exacta.projeto.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "tb_gasto")
public class Gasto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomePessoa;
    private String descricao;
    private Calendar dataHora;
    private Double valor;


    @OneToMany(mappedBy = "gasto")
    private Set<Tag> tags = new HashSet<>();

    public Gasto() {
    }

    public Gasto(Long id, String nomePessoa, String descricao, Calendar dataHora, Double valor) {
        this.id = id;
        this.nomePessoa = nomePessoa;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.valor = valor;
    }

    public Gasto(Long id, String nomePessoa, String descricao, Calendar dataHora, Double valor, Set tags) {
        this.id = id;
        this.nomePessoa = nomePessoa;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.valor = valor;
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
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

    public void setDataHor(Calendar calendar){
        this.dataHora = calendar;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setDataHora(Calendar dataHora) {
        this.dataHora = dataHora;
    }

    public Set getTags() {
        return tags;
    }

    public void setTags(Set tags) {
        this.tags = tags;
    }

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
