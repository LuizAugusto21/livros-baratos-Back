package com.example.livros_baratos_back.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livros_baratos_back.model.Sebo;
import com.example.livros_baratos_back.repository.SeboRepository;

@Service
public class SeboService {
    
    private final SeboRepository seboRepository;

    @Autowired
    public SeboService(SeboRepository seboRepository){
        this.seboRepository = seboRepository;
    }

    public Optional<Sebo> buscarSeboPorId(Long id){
        return seboRepository.findById(id);
    }

    // public Sebo buscarSeboPorNome(String nome){
    //     return seboRepository.findByName();
    // }

    public Sebo salvarSebo(Sebo sebo){
        return seboRepository.save(sebo);
    }

    public Sebo atualizarSebo(Long id, Sebo seboAtualizado){
        Optional<Sebo> seboOptional = seboRepository.findById(id);

        if(seboOptional.isPresent()){
            Sebo sebo = seboOptional.get();
            if(seboAtualizado.getNome() != null){
                sebo.setNome(seboAtualizado.getNome());
            }
            if(seboAtualizado.getLocalizacao() != null){
                sebo.setLocalizacao(seboAtualizado.getLocalizacao());
            }
            if(seboAtualizado.getAcervo() != null){
                sebo.setAcervo(seboAtualizado.getAcervo());
            }
            return seboRepository.save(sebo);
        }
        return null;
    }

    public void deletarSeboPorId(Long id){
        seboRepository.deleteById(id);
    }
}
