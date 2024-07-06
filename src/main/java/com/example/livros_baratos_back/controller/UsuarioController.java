package com.example.livros_baratos_back.controller;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.livros_baratos_back.model.Usuario;
import com.example.livros_baratos_back.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	String notFoundMessage = "Usuario não encontrado";
    private UsuarioRepository usuarioRepository;

    public UsuarioController( UsuarioRepository usuarioRepository ) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("{idUsuario}")
    public Usuario getClienteById( @PathVariable Integer idUsuario ){
        return usuarioRepository
                .findById(idUsuario)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                        		notFoundMessage));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario save( @RequestBody Usuario usuario ){
        return usuarioRepository.save(usuario);
    }

    @DeleteMapping("{idUsuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer idUsuario ){
    	usuarioRepository.findById(idUsuario)
                .map( usuario -> {
                	usuarioRepository.delete(usuario );
                    return usuario;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                		notFoundMessage) );

    }

    @PutMapping("{idUsuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable Integer idUsuario,
                        @RequestBody Usuario usuario ){
    	usuarioRepository
                .findById(idUsuario)
                .map( clienteExistente -> {
                    usuario.setIdUsuario(clienteExistente.getIdUsuario());
                    usuarioRepository.save(usuario);
                    return clienteExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                		notFoundMessage) );
    }

    @GetMapping
    public List<Usuario> find( Usuario filtro ){
        ExampleMatcher matcher = ExampleMatcher
                                    .matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(
                                            ExampleMatcher.StringMatcher.CONTAINING );

        Example<Usuario> example = Example.of(filtro, matcher);
        return usuarioRepository.findAll(example);
    }

}