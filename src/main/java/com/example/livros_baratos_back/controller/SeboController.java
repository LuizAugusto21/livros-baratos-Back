package com.example.livros_baratos_back.controller;

import java.util.List;

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

    @GetMapping
    public List<Sebo> getAllSebos(){
        return seboService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sebo> getSeboById(@PathVariable Long id){
        return seboService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Sebo createSebo(@RequestBody Sebo sebo){
        return seboService.save(sebo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sebo> updateSebo(@PathVariable Long id, @RequestBody Sebo sebo){
        return seboService.findById(id)
                .map(existingSebo -> {
                    sebo.setId(existingSebo.getId());
                    return ResponseEntity.ok(seboService.save(sebo));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSebo(@PathVariable Long id){
        if(seboService.findById(id).isPresent()){
            seboService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
