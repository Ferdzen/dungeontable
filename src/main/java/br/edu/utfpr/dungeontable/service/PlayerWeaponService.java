package br.edu.utfpr.dungeontable.service;

import br.edu.utfpr.dungeontable.exception.BusinessException;
import br.edu.utfpr.dungeontable.exception.ErrorCode;
import br.edu.utfpr.dungeontable.model.interactions.PlayerWeapon;
import br.edu.utfpr.dungeontable.model.vo.PlayerWeaponVO;
import br.edu.utfpr.dungeontable.repository.CampaignRepository;
import br.edu.utfpr.dungeontable.repository.PlayerRepository;
import br.edu.utfpr.dungeontable.repository.PlayerWeaponRepository;
import br.edu.utfpr.dungeontable.repository.WeaponRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerWeaponService {

    @Autowired
    private PlayerWeaponRepository playerWeaponRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private WeaponRepository weaponRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    @Transactional (propagation = Propagation.REQUIRED)
    public PlayerWeapon save(PlayerWeaponVO vo) {
        boolean existsPlayer = playerRepository.findById(vo.getPlayerId()).isPresent();
        boolean existsWeapon = weaponRepository.findById(vo.getWeaponId()).isPresent();
        boolean existsCampaign = campaignRepository.findById(vo.getCampaignId()).isPresent();

        if(!existsPlayer){
            throw new BusinessException(ErrorCode.FORBIDDEN_ACTION, "O player selecionado não existe no sistema");
        }else if(!existsWeapon){
            throw new BusinessException(ErrorCode.FORBIDDEN_ACTION, "A arma selecionada não existe no sistema");
        }else if(!existsCampaign){
            throw new BusinessException(ErrorCode.FORBIDDEN_ACTION, "A campanha selecionada não existe no sistema");
        }

        PlayerWeapon entity = new PlayerWeapon();

        entity.setPlayer(playerRepository.findById(vo.getPlayerId()).orElseThrow());
        entity.setWeapon(weaponRepository.findById(vo.getWeaponId()).orElseThrow());
        entity.setCampaign(campaignRepository.findById(vo.getCampaignId()).orElseThrow());
        entity.setQuantity(vo.getQuantity());

        return playerWeaponRepository.save(entity);
    }

    public List<PlayerWeapon> findAll() {
        return playerWeaponRepository.findAll();
    }

    public List<PlayerWeapon> findByPlayer(Long playerId) {
        return playerWeaponRepository.findByPlayerId(playerId);
    }

    public List<PlayerWeapon> findByPlayerAndCampaign(Long playerId, Long campaignId) {
        return playerWeaponRepository.findByPlayerIdAndCampaignId(playerId, campaignId);
    }

    @Transactional (propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        playerWeaponRepository.deleteById(id);
    }

    @Transactional (propagation = Propagation.REQUIRED)
    public PlayerWeapon updateQuantity(Long playerId, Long weaponId, Long campaignId, Integer quantity) {
        PlayerWeapon weapon = playerWeaponRepository.findAll().stream()
                .filter(pw -> pw.getPlayer().getId().equals(playerId)
                        && pw.getWeapon().getId().equals(weaponId)
                        && pw.getCampaign().getId().equals(campaignId))
                .findFirst()
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND, "player weapon para atualização"));

        weapon.setQuantity(quantity);
        return playerWeaponRepository.save(weapon);
    }
}
