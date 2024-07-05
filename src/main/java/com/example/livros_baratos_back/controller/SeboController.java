package com.example.livros_baratos_back.controller;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

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

import com.example.livros_baratos_back.model.Acervo;
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
    public ResponseEntity<List<Sebo>> listarSebos(){
        List<Sebo> sebos = seboService.listarSebos();
        return ResponseEntity.ok(sebos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Sebo>> buscarSeboPorId(@PathVariable Long id){
        Optional<Sebo> sebo = seboService.buscarSeboPorId(id);
        if(sebo.isPresent()){
            return ResponseEntity.ok(sebo);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    // @GetMapping("/{name}")
    // public ResponseEntity<Sebo> buscarSeboPorNome(@PathVariable String nome){
    //     Sebo sebo = seboService.buscarSeboPorNome(nome);
    //     if(sebo != null){
    //         return ResponseEntity.ok(sebo);
    //     } 
    //     else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }

    @GetMapping("/{id}/acervo")
    public ResponseEntity<Acervo> buscarAcervo(@PathVariable Long id){
        Optional<Sebo> seboOptional = seboService.buscarSeboPorId(id);
        if(seboOptional.isPresent()) {
            Sebo sebo = seboOptional.get();
            return ResponseEntity.ok(sebo.getAcervo());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Sebo> salvarSebo(@RequestBody Sebo sebo){
        Sebo novoSebo = seboService.salvarSebo(sebo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoSebo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sebo> AtualizarSebo(@PathVariable Long id, @RequestBody Sebo seboAtualizado){
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
        seboService.deletarSeboPorId(id);
        return ResponseEntity.noContent().build();
        
    }
}
