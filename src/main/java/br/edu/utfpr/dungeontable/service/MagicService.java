package br.edu.utfpr.dungeontable.service;


import br.edu.utfpr.dungeontable.model.tools.Magic;
import br.edu.utfpr.dungeontable.repository.MagicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MagicService {

    @Autowired
    private MagicRepository magicRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public Magic save(Magic magic) {
        return magicRepository.save(magic);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Magic update(Magic magic) {
        return magicRepository.save(magic);
    }

    public Magic findById(Long id) {
        return magicRepository.findById(id).orElse(null);
    }

    public List<Magic> findAll() {
        return magicRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        magicRepository.deleteById(id);
    }
}
