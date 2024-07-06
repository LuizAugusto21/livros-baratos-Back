package com.example.livros_baratos_back.controller;


import com.example.livros_baratos_back.model.Livro;
import com.example.livros_baratos_back.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;


    @GetMapping
    public List<Livro> listarTodos() {
        return livroService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id) {
        Optional<Livro> livro = livroService.buscarPorId(id);
        return livro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/buscarPorNome")
    public ResponseEntity<List<Livro>> buscarPorNome(@RequestParam String nome) {
        List<Livro> livros = livroService.buscarPorNome(nome);
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/buscarPorGenero")
    public ResponseEntity<List<Livro>> buscarPorGenero(@RequestParam String genero) {
        List<Livro> livros = livroService.buscarPorGenero(genero);
        return ResponseEntity.ok(livros);
    }

    @PostMapping
    public ResponseEntity<Livro> salvar(@RequestBody Livro livro) {
        Livro livroSalvo = livroService.salvar(livro);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(livroSalvo.getId())
                .toUri();
        return ResponseEntity.created(location).body(livroSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id, @RequestBody Livro livroAtualizado) {
        Optional<Livro> livro = livroService.buscarPorId(id);
        if (livro.isPresent()) {
            livroAtualizado.setId(id);
            return ResponseEntity.ok(livroService.salvar(livroAtualizado));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (livroService.buscarPorId(id).isPresent()) {
            livroService.deletar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

