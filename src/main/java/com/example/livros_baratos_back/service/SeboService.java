package com.example.livros_baratos_back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livros_baratos_back.model.Sebo;
import com.example.livros_baratos_back.repository.SeboRepository;

@Service
public class SeboService {
    
    @Autowired
    private SeboRepository seboRepository;

    public List<Sebo> findAll(){
        return seboRepository.findAll();
    }

    public Optional<Sebo> findById(Long id){
        return seboRepository.findById(id);
    }
}
