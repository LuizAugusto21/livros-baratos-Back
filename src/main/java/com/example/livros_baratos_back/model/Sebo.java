package com.example.livros_baratos_back.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SEBO")
public class Sebo extends Usuario {

    private String nome;
    private String localizacao;
    private String cnpj;
//    private Acervo acervo;

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getLocalizacao() {
        return localizacao;
    }
    
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

//    public Acervo getAcervo() {
//        return acervo;
//    }
//
//    public void setAcervo(Acervo acervo) {
//        this.acervo = acervo;
//    }

}
