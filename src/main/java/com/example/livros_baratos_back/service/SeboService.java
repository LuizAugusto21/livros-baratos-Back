package com.example.livros_baratos_back.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.livros_baratos_back.model.Sebo;

@Service
public class SeboService {
    
    private final List<Sebo> sebos = new ArrayList<>();
    private long currentId = 1;

    public List<Sebo> listarSebos(){
        return new ArrayList<>(sebos);
    }

    public Sebo buscarSeboPorId(Long id){
        return sebos.stream().filter(sebo -> sebo.getId().equals(id)).findFirst().orElse(null);
    }

    public Sebo salvarSebo(Sebo sebo){
        sebo.setId(currentId++);
        sebos.add(sebo); 
        return sebo;
    }

    public Sebo atualizarSebo(Long id, Sebo seboAtualizado){
        Optional<Sebo> seboOptional = sebos.stream().filter(sebo -> sebo.getId().equals(id)).findFirst();

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
            return sebo;
        }
        return null;
    }

    public void deletarSeboPorId(Long id){
        sebos.removeIf(sebo -> sebo.getId().equals(id));
    }
}
