package com.example.livros_baratos_back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.livros_baratos_back.model.Livro;
import com.example.livros_baratos_back.service.AcervoService;

@RestController
@RequestMapping("/acervo")
public class AcervoController {
    
    private final AcervoService acervoService;

    @Autowired
    public AcervoController(AcervoService acervoService){
        this.acervoService = acervoService;
    }

    @GetMapping("/livros")
    public ResponseEntity<List<Livro>> listarAcervos(){
        List<Livro> livros = acervoService.listarLivros();
        return ResponseEntity.ok(livros);
    }


    @GetMapping ("/livros/{id}")
    public ResponseEntity<Livro> buscarLivroPorId(@PathVariable Long id){
        Livro livro = acervoService.buscarLivroPorId(id);
        if(livro != null){
            return ResponseEntity.ok(livro);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/livros")
    public ResponseEntity<Livro> adicionarLivro(@RequestBody Livro livro){
        Livro novoLivro = acervoService.adicionarLivro(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoLivro);
    }

    @PutMapping("livros/{id}")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable Long id, @RequestBody Livro livroAtualizado){
        Livro livro = acervoService.atualizarLivro(id, livroAtualizado);
        if(livro != null){
            return ResponseEntity.ok(livro);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/livros/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id){
        acervoService.deletarLivro(id);
        return ResponseEntity.noContent().build();
    }
}

