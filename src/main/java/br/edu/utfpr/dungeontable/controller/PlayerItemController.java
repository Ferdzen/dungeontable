package br.edu.utfpr.dungeontable.controller;

import br.edu.utfpr.dungeontable.model.interactions.PlayerItem;
import br.edu.utfpr.dungeontable.model.vo.PlayerItemVO;
import br.edu.utfpr.dungeontable.model.vo.UpdatePlayerItemQuantityVO;
import br.edu.utfpr.dungeontable.service.PlayerItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/player-items")
public class PlayerItemController {

    @Autowired
    private PlayerItemService playerItemService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<PlayerItemVO> create(@RequestBody PlayerItemVO vo) {
        PlayerItem saved = playerItemService.save(vo);
        return new ResponseEntity<>(modelMapper.map(saved, PlayerItemVO.class), HttpStatus.CREATED);
    }

    @GetMapping
    public List<PlayerItemVO> findAll() {
        return playerItemService.findAll().stream()
                .map(item -> modelMapper.map(item, PlayerItemVO.class))
                .toList();
    }

    @GetMapping("/player/{playerId}")
    public List<PlayerItemVO> findByPlayer(@PathVariable Long playerId) {
        return playerItemService.findByPlayer(playerId).stream()
                .map(item -> modelMapper.map(item, PlayerItemVO.class))
                .toList();
    }

    @GetMapping("/player/{playerId}/campaign/{campaignId}")
    public List<PlayerItemVO> findByPlayerAndCampaign(@PathVariable Long playerId, @PathVariable Long campaignId) {
        return playerItemService.findByPlayerAndCampaign(playerId, campaignId).stream()
                .map(item -> modelMapper.map(item, PlayerItemVO.class))
                .toList();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        playerItemService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/quantity")
    public ResponseEntity<PlayerItemVO> updateQuantity(@RequestBody UpdatePlayerItemQuantityVO vo) {
        PlayerItem updated = playerItemService.updateQuantity(
                vo.getPlayerId(), vo.getItemId(), vo.getCampaignId(), vo.getQuantity());

        return ResponseEntity.ok(modelMapper.map(updated, PlayerItemVO.class));
    }
}
