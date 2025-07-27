package br.edu.utfpr.dungeontable.service;

import br.edu.utfpr.dungeontable.exception.BusinessException;
import br.edu.utfpr.dungeontable.exception.ErrorCode;
import br.edu.utfpr.dungeontable.model.interactions.PlayerMagic;
import br.edu.utfpr.dungeontable.model.table.ClassPlayer;
import br.edu.utfpr.dungeontable.model.table.Player;
import br.edu.utfpr.dungeontable.model.vo.PlayerMagicVO;
import br.edu.utfpr.dungeontable.repository.CampaignRepository;
import br.edu.utfpr.dungeontable.repository.MagicRepository;
import br.edu.utfpr.dungeontable.repository.PlayerMagicRepository;
import br.edu.utfpr.dungeontable.repository.PlayerRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerMagicService {

    @Autowired
    private PlayerMagicRepository playerMagicRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private MagicRepository magicRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    @Transactional (propagation = Propagation.REQUIRED)
    public PlayerMagic save(PlayerMagicVO vo) {
        Player player = playerRepository.findById(vo.getPlayerId())
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND, "player"));

        List<Long> idsDeClassesComMagia = List.of(2L, 3L, 4L, 5L, 6L, 9L, 11L);
        ClassPlayer classPlayer = player.getClassPlayer();
        if(classPlayer == null || !idsDeClassesComMagia.contains(classPlayer.getId())){
            throw new BusinessException(ErrorCode.FORBIDDEN_ACTION,
                    "A classe não pode usar magias.");
        }

        PlayerMagic entity = new PlayerMagic();
        entity.setPlayer(playerRepository.findById(vo.getPlayerId()).orElseThrow());
        entity.setMagic(magicRepository.findById(vo.getMagicId()).orElseThrow());
        entity.setCampaign(campaignRepository.findById(vo.getCampaignId()).orElseThrow());

        return playerMagicRepository.save(entity);
    }

    public List<PlayerMagic> findAll() {
        return playerMagicRepository.findAll();
    }

    public List<PlayerMagic> findByPlayer(Long playerId) {
        return playerMagicRepository.findByPlayerId(playerId);
    }

    public List<PlayerMagic> findByPlayerAndCampaign(Long playerId, Long campaignId) {
        return playerMagicRepository.findByPlayerIdAndCampaignId(playerId, campaignId);
    }

    @Transactional (propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        playerMagicRepository.deleteById(id);
    }
}

