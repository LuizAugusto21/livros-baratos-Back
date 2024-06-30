package com.example.livros_baratos_back.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.livros_baratos_back.model.Sebo;
import com.example.livros_baratos_back.service.SeboService;

@RestController
@RequestMapping("/sebos")
public class SeboController {

    @Autowired
    private SeboService seboService;
    
    // CRUD do SEBO

    @GetMapping
    public List<Sebo> getAllSebos(){
        return seboService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Sebo> getSeboById(@PathVariable Long id){
        return seboService.findById(id);
    }

    

}
