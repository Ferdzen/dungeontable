package br.edu.utfpr.dungeontable.controller;

import br.edu.utfpr.dungeontable.model.interactions.PlayerMagic;
import br.edu.utfpr.dungeontable.model.vo.PlayerMagicVO;
import br.edu.utfpr.dungeontable.service.PlayerMagicService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/player-magics")
public class PlayerMagicController {

    @Autowired
    private PlayerMagicService playerMagicService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<PlayerMagicVO> create(@RequestBody PlayerMagicVO vo) {
        PlayerMagic saved = playerMagicService.save(vo);
        return new ResponseEntity<>(modelMapper.map(saved, PlayerMagicVO.class), HttpStatus.CREATED);
    }

    @GetMapping
    public List<PlayerMagicVO> findAll() {
        return playerMagicService.findAll().stream()
                .map(magic -> modelMapper.map(magic, PlayerMagicVO.class))
                .toList();
    }

    @GetMapping("/player/{playerId}")
    public List<PlayerMagicVO> findByPlayer(@PathVariable Long playerId) {
        return playerMagicService.findByPlayer(playerId).stream()
                .map(magic -> modelMapper.map(magic, PlayerMagicVO.class))
                .toList();
    }

    @GetMapping("/player/{playerId}/campaign/{campaignId}")
    public List<PlayerMagicVO> findByPlayerAndCampaign(@PathVariable Long playerId, @PathVariable Long campaignId) {
        return playerMagicService.findByPlayerAndCampaign(playerId, campaignId).stream()
                .map(magic -> modelMapper.map(magic, PlayerMagicVO.class))
                .toList();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        playerMagicService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
