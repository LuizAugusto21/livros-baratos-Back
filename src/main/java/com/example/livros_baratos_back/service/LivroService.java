package com.example.livros_baratos_back.service;

import com.example.livros_baratos_back.model.Livro;
import com.example.livros_baratos_back.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    @Autowired
    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    public Optional<Livro> buscarPorId(Long id) {
        return livroRepository.findById(id);
    }

    public Livro salvar(Livro livro) {
        return livroRepository.save(livro);
    }

    public void deletar(Long id) {
        livroRepository.deleteById(id);
    }

    @Transactional
    public List<Livro> buscarPorNome(String nome) {
        return livroRepository.findByNomeContaining(nome);
    }
    @Transactional
    public List<Livro> buscarPorGenero(String genero) {
        return livroRepository.findByGeneroContaining(genero);
    }
    
    
}