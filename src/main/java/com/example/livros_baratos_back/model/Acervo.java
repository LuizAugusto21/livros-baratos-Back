//package com.example.livros_baratos_back.model;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
//
//@Entity
//public class Acervo {
//
//    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
//    private Long id;
//
//    @OneToMany(mappedBy="acervo", cascade=CascadeType.ALL)
//    private List<Livro> livros = new ArrayList<>();
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public List<Livro> getLivros() {
//        return livros;
//    }
//
//    public void setLivros(List<Livro> livros) {
//        this.livros = livros;
//    }
//
//}
