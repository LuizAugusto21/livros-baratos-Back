package com.example.livros_baratos_back.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.livros_baratos_back.model.Livro;
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

import com.example.livros_baratos_back.exception.UserAlreadyExistsException;
import com.example.livros_baratos_back.model.Pessoa;
import com.example.livros_baratos_back.model.Sebo;
import com.example.livros_baratos_back.model.Usuario;
import com.example.livros_baratos_back.service.UsuarioService;
import com.example.livros_baratos_back.service.LivroService;
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LivroService livroService;

    @GetMapping("{idUsuario}")
    public ResponseEntity<Usuario> getClienteById(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(usuarioService.getUsuarioById(idUsuario));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Usuario usuario) {
        try {
            Usuario savedUsuario = usuarioService.saveUsuario(usuario);
            return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("{idUsuario}")
    public ResponseEntity<Void> delete(@PathVariable Integer idUsuario) {
        usuarioService.deleteUsuario(idUsuario);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{idUsuario}")
    public ResponseEntity<Void> update(@PathVariable Integer idUsuario, @RequestBody Usuario usuario) {
        usuarioService.updateUsuario(idUsuario, usuario);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> find(Usuario filtro) {
        return ResponseEntity.ok(usuarioService.findUsuarios(filtro));
    }

    @PostMapping("/pessoas")
    public ResponseEntity<Pessoa> addPessoa(@RequestBody Pessoa pessoa) {
        Pessoa savedPessoa = usuarioService.savePessoa(pessoa);
        return new ResponseEntity<>(savedPessoa, HttpStatus.CREATED);
    }

    @PostMapping("/sebos")
    public ResponseEntity<Sebo> addSebo(@RequestBody Sebo sebo) {
        Sebo savedSebo = usuarioService.saveSebo(sebo);
        return new ResponseEntity<>(savedSebo, HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario loginRequest) {
        Optional<Usuario> usuarioOptional = usuarioService.findByLogin(loginRequest.getLogin());
        if (usuarioOptional.isPresent() && usuarioOptional.get().getSenha().equals(loginRequest.getSenha())) {
            return ResponseEntity.ok(usuarioOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas.");
        }
    }


    @GetMapping("/{id}/favoritos")
    public ResponseEntity<ArrayList<Livro>> getFavoritos(@PathVariable Integer id) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario instanceof Pessoa) {
            Pessoa pessoa = (Pessoa) usuario;
            return ResponseEntity.ok(pessoa.getFavoritos());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/favoritos")
    public ResponseEntity<Pessoa> addFavorito(@PathVariable Integer id, @RequestBody Long livroId) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        Optional<Livro> livroOptional = livroService.buscarPorId(livroId);

        System.out.println(livroOptional);

        if (usuario instanceof Pessoa && livroOptional.isPresent()) {
            Pessoa pessoa = (Pessoa) usuario;
            Livro livro = livroOptional.get();

            pessoa.getFavoritos().add(livro);
            usuarioService.savePessoa(pessoa);

            return ResponseEntity.ok(pessoa);
        } else if (livroOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.notFound().build(); // Usuário não é uma Pessoa
        }
    }

    @DeleteMapping("/{id}/favoritos/{livroId}")
    public ResponseEntity<Pessoa> removeFavorito(@PathVariable Integer id, @PathVariable Long livroId) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario instanceof Pessoa) {
            Pessoa pessoa = (Pessoa) usuario;
            pessoa.getFavoritos().removeIf(livro -> livro.getId().equals(livroId));
            usuarioService.savePessoa(pessoa);
            return ResponseEntity.ok(pessoa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
