package br.edu.utfpr.dungeontable.controller;

import br.edu.utfpr.dungeontable.model.table.Player;
import br.edu.utfpr.dungeontable.model.vo.PlayerVO;
import br.edu.utfpr.dungeontable.service.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/player")
public class PlayerController {
    public PlayerController() {}

    @Autowired
    private PlayerService playerService;
    private ModelMapper modelMapper = new ModelMapper();

    @PostMapping
    public ResponseEntity<PlayerVO> save(@RequestBody PlayerVO playerVO) {
        Player player = modelMapper.map(playerVO, Player.class);
        playerService.save(player);
        playerVO.setId(player.getId());
        return new ResponseEntity<>(playerVO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerVO> update(@PathVariable("id") Long id, @RequestBody PlayerVO playerVO) {
        Player player = modelMapper.map(playerVO, Player.class);
        player.setId(id);
        playerService.update(player);
        return new ResponseEntity<>(playerVO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public PlayerVO findById(@PathVariable("id") Long id) {
        return modelMapper.map(playerService.findById(id), PlayerVO.class);
    }

    @GetMapping
    public ResponseEntity<List<PlayerVO>> findAll() {
        List<Player> players = playerService.findAll();
        List<PlayerVO> playerVOs = players.stream().map(player -> modelMapper.map(player, PlayerVO.class))
                .toList();
        return new ResponseEntity<>(playerVOs, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        playerService.delete(id);
    }
}
