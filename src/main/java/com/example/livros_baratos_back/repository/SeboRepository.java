package com.example.livros_baratos_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.livros_baratos_back.model.Sebo;

@Repository
public interface SeboRepository extends JpaRepository<Sebo, Long> {
    
}
