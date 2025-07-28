package br.edu.utfpr.dungeontable.controller;

import br.edu.utfpr.dungeontable.model.interactions.PlayerWeapon;
import br.edu.utfpr.dungeontable.model.vo.PlayerWeaponVO;
import br.edu.utfpr.dungeontable.model.vo.UpdatePlayerWeaponQuantityVO;
import br.edu.utfpr.dungeontable.service.PlayerWeaponService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/player-weapons")
public class PlayerWeaponController {

    @Autowired
    private PlayerWeaponService playerWeaponService;

    @Autowired
    private ModelMapper modelMapper;

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @PostMapping
    public ResponseEntity<PlayerWeaponVO> create(@RequestBody PlayerWeaponVO vo) {
        PlayerWeapon saved = playerWeaponService.save(vo);
        return new ResponseEntity<>(modelMapper.map(saved, PlayerWeaponVO.class), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping
    public List<PlayerWeaponVO> findAll() {
        return playerWeaponService.findAll().stream()
                .map(weapon -> modelMapper.map(weapon, PlayerWeaponVO.class))
                .toList();
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("/player/{playerId}")
    public List<PlayerWeaponVO> findByPlayer(@PathVariable Long playerId) {
        return playerWeaponService.findByPlayer(playerId).stream()
                .map(weapon -> modelMapper.map(weapon, PlayerWeaponVO.class))
                .toList();
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("/player/{playerId}/campaign/{campaignId}")
    public List<PlayerWeaponVO> findByPlayerAndCampaign(@PathVariable Long playerId, @PathVariable Long campaignId) {
        return playerWeaponService.findByPlayerAndCampaign(playerId, campaignId).stream()
                .map(weapon -> modelMapper.map(weapon, PlayerWeaponVO.class))
                .toList();
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        playerWeaponService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @PatchMapping("/quantity")
    public ResponseEntity<PlayerWeaponVO> updateQuantity(@RequestBody UpdatePlayerWeaponQuantityVO vo) {
        PlayerWeapon updated = playerWeaponService.updateQuantity(
                vo.getPlayerId(), vo.getWeaponId(), vo.getCampaignId(), vo.getQuantity());

        return ResponseEntity.ok(modelMapper.map(updated, PlayerWeaponVO.class));
    }
}

