package com.example.livros_baratos_back.repository;

import com.example.livros_baratos_back.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}
