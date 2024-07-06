package com.example.livros_baratos_back.repository;

import com.example.livros_baratos_back.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByNomeContaining(String nome);
    List<Livro> findByGeneroContaining(String genero);
}