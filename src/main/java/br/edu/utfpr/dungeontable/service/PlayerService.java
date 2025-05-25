package br.edu.utfpr.dungeontable.service;


import br.edu.utfpr.dungeontable.model.table.Player;
import br.edu.utfpr.dungeontable.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public Player save(Player player) {
        return playerRepository.save(player);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Player update(Player player) {
        return playerRepository.save(player);
    }

    public Player findById(Long id) {
        return playerRepository.findById(id).orElse(null);
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        playerRepository.deleteById(id);
    }
}
