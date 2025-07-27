package br.edu.utfpr.dungeontable.service;

import br.edu.utfpr.dungeontable.exception.BusinessException;
import br.edu.utfpr.dungeontable.exception.ErrorCode;
import br.edu.utfpr.dungeontable.model.interactions.PlayerItem;
import br.edu.utfpr.dungeontable.model.vo.PlayerItemVO;
import br.edu.utfpr.dungeontable.repository.CampaignRepository;
import br.edu.utfpr.dungeontable.repository.ItemRepository;
import br.edu.utfpr.dungeontable.repository.PlayerItemRepository;
import br.edu.utfpr.dungeontable.repository.PlayerRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerItemService {

    @Autowired
    private PlayerItemRepository playerItemRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    @Transactional (propagation = Propagation.REQUIRED)
    public PlayerItem save(PlayerItemVO vo) {
        boolean existsPlayer = playerRepository.findById(vo.getPlayerId()).isPresent();
        boolean existsItem = itemRepository.findById(vo.getItemId()).isPresent();
        boolean existsCampaign = campaignRepository.findById(vo.getCampaignId()).isPresent();

        if(!existsPlayer){
            throw new BusinessException(ErrorCode.FORBIDDEN_ACTION, "O player selecionado não existe no sistema.");
        }else if(!existsItem){
            throw new BusinessException(ErrorCode.FORBIDDEN_ACTION, "O item selecionado não existe no sistema.");
        }else if(!existsCampaign){
            throw new BusinessException(ErrorCode.FORBIDDEN_ACTION, "A campanha selecionada não existe no sistema.");
        }

        PlayerItem entity = new PlayerItem();

        entity.setPlayer(playerRepository.findById(vo.getPlayerId()).orElseThrow());
        entity.setItem(itemRepository.findById(vo.getItemId()).orElseThrow());
        entity.setCampaign(campaignRepository.findById(vo.getCampaignId()).orElseThrow());
        entity.setQuantity(vo.getQuantity());

        return playerItemRepository.save(entity);
    }

    public List<PlayerItem> findAll() {
        return playerItemRepository.findAll();
    }

    public List<PlayerItem> findByPlayer(Long playerId) {
        return playerItemRepository.findByPlayerId(playerId);
    }

    public List<PlayerItem> findByPlayerAndCampaign(Long playerId, Long campaignId) {
        return playerItemRepository.findByPlayerIdAndCampaignId(playerId, campaignId);
    }

    @Transactional (propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        playerItemRepository.deleteById(id);
    }

    @Transactional (propagation = Propagation.REQUIRED)
    public PlayerItem updateQuantity(Long playerId, Long itemId, Long campaignId, Integer quantity) {
        PlayerItem item = playerItemRepository.findAll().stream()
                .filter(pi -> pi.getPlayer().getId().equals(playerId)
                        && pi.getItem().getId().equals(itemId)
                        && pi.getCampaign().getId().equals(campaignId))
                .findFirst()
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND, "player item para atualização"));

        item.setQuantity(quantity);
        return playerItemRepository.save(item);
    }
}

