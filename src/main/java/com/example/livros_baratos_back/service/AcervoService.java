package com.example.livros_baratos_back.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.livros_baratos_back.model.Livro;

@Service
public class AcervoService {
    
    private final List<Livro> livros = new ArrayList<>();
    private long currentId = 1;

    public List<Livro> listarLivros(){
        return new ArrayList<>(livros);
    }

    public Livro buscarLivroPorId(Long id){
        return livros.stream().filter(livro -> livro.getId().equals(id)).findFirst().orElse(null);
    }

    public Livro adicionarLivro(Livro livro){
        livro.setId(currentId++);
        livros.add(livro);
        return livro;        
    }    
    
    public Livro atualizarLivro(Long id, Livro livroAtualizado){
        Optional<Livro> livroOptional = livros.stream().filter(livro -> livro.getId().equals(id)).findFirst();

        if(livroOptional.isPresent()){
            Livro livro = livroOptional.get();
            if(livroAtualizado.getTitulo() != null){
                livro.setTitulo(livroAtualizado.getTitulo());
            }
            if(livroAtualizado.getAutor() != null){
                livro.setAutor(livroAtualizado.getAutor());
            }
            if(livroAtualizado.getPreco() != null){
                livro.setPreco(livroAtualizado.getPreco());
            }
            if(livroAtualizado.getGenero() != null){
                livro.setGenero(livroAtualizado.getGenero());
            }
            if(livroAtualizado.getAno() != null){
                livro.setAno(livroAtualizado.getAno());
            }
            return livro;
        }
        return null;
    }

    public void deletarLivro(Long id){
        livros.removeIf(livro -> livro.getId().equals(id));
    }

}
