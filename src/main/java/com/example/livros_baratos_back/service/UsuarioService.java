package com.example.livros_baratos_back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.livros_baratos_back.exception.UserAlreadyExistsException;
import com.example.livros_baratos_back.model.Pessoa;
import com.example.livros_baratos_back.model.Sebo;
import com.example.livros_baratos_back.model.Usuario;
import com.example.livros_baratos_back.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final String notFoundMessage = "Usuario não encontrado";

    public Usuario saveUsuario(Usuario usuario) {
        if (usuarioRepository.findByLogin(usuario.getLogin()).isPresent()) {
            throw new UserAlreadyExistsException("Um usuário com este login já existe.");
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario getUsuarioById(Integer idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, notFoundMessage));
    }

    public void deleteUsuario(Integer idUsuario) {
        usuarioRepository.findById(idUsuario)
                .map(usuario -> {
                    usuarioRepository.delete(usuario);
                    return usuario;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, notFoundMessage));
    }

    public void updateUsuario(Integer idUsuario, Usuario usuario) {
        usuarioRepository.findById(idUsuario)
                .map(clienteExistente -> {
                    usuario.setIdUsuario(clienteExistente.getIdUsuario());
                    usuarioRepository.save(usuario);
                    return clienteExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, notFoundMessage));
    }

    public List<Usuario> findUsuarios(Usuario filtro) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Usuario> example = Example.of(filtro, matcher);
        return usuarioRepository.findAll(example);
    }

    public Pessoa savePessoa(Pessoa pessoa) {
        return usuarioRepository.save(pessoa);
    }

    public Sebo saveSebo(Sebo sebo) {
        return usuarioRepository.save(sebo);
    }
    
    public Optional<Usuario> findByLogin(String login) {
        return usuarioRepository.findByLogin(login);
    }

}
