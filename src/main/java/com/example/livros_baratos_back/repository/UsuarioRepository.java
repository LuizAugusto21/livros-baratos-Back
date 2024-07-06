package com.example.livros_baratos_back.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.livros_baratos_back.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
}
