package com.example.livros_baratos_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.livros_baratos_back.model.Livro;

public interface AcervoRepository extends  JpaRepository<Livro, Long>{
    
}
