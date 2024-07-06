package com.example.livros_baratos_back.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.livros_baratos_back.model.Sebo;
import com.example.livros_baratos_back.service.SeboService;

@RestController
@RequestMapping("/sebos")
public class SeboController {

    private final SeboService seboService;

    @Autowired
    public SeboController(SeboService seboService) {
        this.seboService = seboService;
    }

    @GetMapping("/")
    public List<Sebo> listarSebos(){
        return seboService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sebo> buscarSeboPorId(@PathVariable Long id){
        Optional<Sebo> sebo = seboService.buscarSeboPorId(id);
        return sebo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // INCOMPLETO - REFAZER - NÃO ADAPTADO
    // Falta a lógica de buscar por nome
    /*
    @GetMapping("/{name}")
    public ResponseEntity<Sebo> buscarSeboPorNome(@PathVariable String nome){
        Sebo sebo = seboService.buscarSeboPorNome(nome);
        if(sebo != null){
            return ResponseEntity.ok(sebo);
        } 
        else {
            return ResponseEntity.notFound().build();
        }
    }
    */


    // INCOMPLETO - REFAZER - NÃO ADAPTADO
    // Pesquisar no ChatGPT
    /*
    @GetMapping("/{id}/acervo")
    public ResponseEntity<Acervo> buscarAcervo(@PathVariable Long id){
        // Optional<Sebo> seboOpt = seboService.buscarSeboPorId(id);
        // if(seboOpt.isPresent()){
        //     Acervo acervo = seboOpt.get().getAcervo();
        //     return ResponseEntity.ok();
        // }

        return new Acervo();
    }
    */

    @PostMapping
    public ResponseEntity<Sebo> salvarSebo(@RequestBody Sebo sebo){
        Sebo seboSalvo = seboService.salvarSebo(sebo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(seboSalvo.getId())
            .toUri();
        return ResponseEntity.created(location).body(seboSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sebo> atualizarSebo(@PathVariable Long id, @RequestBody Sebo seboAtualizado){
        Sebo sebo = seboService.atualizarSebo(id, seboAtualizado);
        if(sebo != null){
            return ResponseEntity.ok(sebo);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSebo(@PathVariable Long id){
        if(seboService.buscarSeboPorId(id).isPresent()){
            seboService.deletarSeboPorId(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
