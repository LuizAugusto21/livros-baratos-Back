package com.example.livros_baratos_back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livros_baratos_back.model.Livro;
import com.example.livros_baratos_back.repository.AcervoRepository;

@Service
public class AcervoService {
    
    private final AcervoRepository acervoRepository;

    @Autowired
    public AcervoService(AcervoRepository acervoRepository){
        this.acervoRepository = acervoRepository;
    }

    public List<Livro> listarLivros(){
        return acervoRepository.findAll();
    }

    public Livro buscarLivroPorId(Long id){
        return acervoRepository.findById(id).orElse(null);
    }

    public Livro adicionarLivro( Livro livro){
        return acervoRepository.save(livro);
    }

    public Livro atualizarLivro(Long id, Livro livroAtualizado){
        Livro livroExistente = acervoRepository.findById(id).orElse(null);
        if(livroExistente != null){
            livroExistente.setTitulo(livroAtualizado.getTitulo());
            livroExistente.setAutor(livroAtualizado.getAutor());
            livroExistente.setAno(livroAtualizado.getAno());
            livroExistente.setGenero(livroAtualizado.getGenero());
            return acervoRepository.save(livroAtualizado);
        }
        else {
            return null;
        }
    }

    public void deletarLivro(Long id){
        acervoRepository.deleteById(id);
    }

}
