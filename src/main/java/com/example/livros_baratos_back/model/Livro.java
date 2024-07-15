package com.example.livros_baratos_back.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.annotation.Generated;
import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    private String descricao;

    private String nomeAutor;

    private String genero;


    private String imagemCapa;

    private double preco;


    @Enumerated(EnumType.STRING)
    private EnumCondicao condicao;

    @Enumerated(EnumType.STRING)
    private EnumSituacao situacao;

    @OneToMany(mappedBy = "itemMovimentado", cascade = CascadeType.ALL)
    private List<Movimentacoes> movimentacoes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getImagemCapa() {
        return imagemCapa;
    }

    public void setImagemCapa(String imagemCapa) {
        this.imagemCapa = imagemCapa;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public EnumCondicao getCondicao() {
        return condicao;
    }

    public void setCondicao(EnumCondicao condicao) {
        this.condicao = condicao;
    }

    public EnumSituacao getSituacao() {
        return situacao;
    }

    public void setSituacao(EnumSituacao situacao) {
        this.situacao = situacao;
    }
}