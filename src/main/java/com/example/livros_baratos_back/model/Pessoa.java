package com.example.livros_baratos_back.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.ArrayList;

@Entity
@DiscriminatorValue("PESSOA")
public class Pessoa extends Usuario {

    private String cpf;
    private ArrayList<Livro> carrinho;
    private ArrayList<Livro> favoritos;
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public ArrayList<Livro> getCarrinho() {
        return carrinho;
    }
    
    public void setCarrinho(ArrayList<Livro> carrinho) {
        this.carrinho = carrinho;
    }
    
    public ArrayList<Livro> getFavoritos() {
        return favoritos;
    }
    
    public void setFavoritos(ArrayList<Livro> favoritos) {
        this.favoritos = favoritos;
    }
}
