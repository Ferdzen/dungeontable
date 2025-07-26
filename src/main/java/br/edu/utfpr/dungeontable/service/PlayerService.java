package br.edu.utfpr.dungeontable.service;


import br.edu.utfpr.dungeontable.exception.BusinessException;
import br.edu.utfpr.dungeontable.exception.ErrorCode;
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
    public Player save(Player player) throws BusinessException {
        if (player.getNamePlayer() == null || player.getNamePlayer().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "name player");
        }else if (player.getNameCharacter() == null || player.getNameCharacter().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "name character");
        } else if (player.getClassCharacter() == null || player.getClassCharacter().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "class character");
        } else if (player.getAntecedent() == null || player.getAntecedent().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "antecedent");
        } else if (player.getRaceCharacter() == null || player.getRaceCharacter().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "race character");
        } else if (player.getAge() == null) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "age");
        }
        return playerRepository.save(player);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Player update(Player player) {
        if(player.getId() == null){
            throw new BusinessException(ErrorCode.ID_REQUIRED);
        }
        else if (player.getNamePlayer() == null || player.getNamePlayer().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "name player");
        }else if (player.getNameCharacter() == null || player.getNameCharacter().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "name character");
        } else if (player.getClassCharacter() == null || player.getClassCharacter().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "class character");
        } else if (player.getAntecedent() == null || player.getAntecedent().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "antecedent");
        } else if (player.getRaceCharacter() == null || player.getRaceCharacter().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "race character");
        } else if (player.getAge() == null) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "age");
        }
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
        if(id == null){
            throw new BusinessException(ErrorCode.ID_REQUIRED);
        }
        playerRepository.deleteById(id);
    }
}
