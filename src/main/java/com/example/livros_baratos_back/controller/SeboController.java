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

    @GetMapping("/sebos")
    public ResponseEntity<List<Sebo>> listarSebos(){
        List<Sebo> sebos = seboService.listarSebos();
        return ResponseEntity.ok(sebos);
    }

    @GetMapping("/sebos/{id}")
    public ResponseEntity<Sebo> buscarPorId(@PathVariable Long id){
        Sebo sebo = seboService.buscarSeboPorId(id);
        if( sebo != null){
            return ResponseEntity.ok(sebo);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/sebos")
    public ResponseEntity<Sebo> salvarSebo(@RequestBody Sebo sebo){
        Sebo novoSebo = seboService.salvarSebo(sebo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoSebo);
    }

    @PutMapping("/sebos/{id}")
    public ResponseEntity<Sebo> AtualizarSebo(@PathVariable Long id, @RequestBody Sebo seboAtualizado){
        Sebo sebo = seboService.atualizarSebo(id, seboAtualizado);
        if(sebo != null){
            return ResponseEntity.ok(sebo);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/sebos/{id}")
    public ResponseEntity<Void> deletarSebo(@PathVariable Long id){
        seboService.deletarSeboPorId(id);
        return ResponseEntity.noContent().build();
        
    }
}
