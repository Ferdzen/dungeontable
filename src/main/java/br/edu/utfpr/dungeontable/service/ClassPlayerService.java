package br.edu.utfpr.dungeontable.service;

import br.edu.utfpr.dungeontable.model.table.ClassPlayer;
import br.edu.utfpr.dungeontable.repository.ClassPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassPlayerService {
    @Autowired
    private ClassPlayerRepository classPlayerRepository;

    public ClassPlayer findById(Long Id){
        return classPlayerRepository.findById(Id).orElse(null);
    }

    public List<ClassPlayer> findAll(){
        return classPlayerRepository.findAll();
    }
}
