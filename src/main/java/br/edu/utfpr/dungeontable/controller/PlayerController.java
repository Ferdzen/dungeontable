package br.edu.utfpr.dungeontable.controller;

import br.edu.utfpr.dungeontable.exception.BusinessException;
import br.edu.utfpr.dungeontable.exception.NotFoundException;
import br.edu.utfpr.dungeontable.model.User;
import br.edu.utfpr.dungeontable.model.table.Player;
import br.edu.utfpr.dungeontable.model.vo.PlayerVO;
import br.edu.utfpr.dungeontable.service.PlayerService;
import br.edu.utfpr.dungeontable.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/player")
public class PlayerController {
    public PlayerController() {}

    @Autowired
    private PlayerService playerService;
    @Autowired
    private ModelMapper modelMapper;

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @PostMapping
    public ResponseEntity<PlayerVO> save(@RequestBody PlayerVO playerVO) {
        Player player = modelMapper.map(playerVO, Player.class);
        playerService.save(player);
        playerVO.setId(player.getId());
        return new ResponseEntity<>(playerVO, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @PutMapping("/{id}")
    public ResponseEntity<PlayerVO> update(@PathVariable("id") Long id, @RequestBody PlayerVO playerVO) {
        Player player = modelMapper.map(playerVO, Player.class);
        player.setId(id);
        playerService.update(player);
        return new ResponseEntity<>(playerVO, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("/{id}")
    @Operation(summary = "Get player by ID", description = "Returns a single player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404",  description = "Not found - The player was not found")
    })
    public PlayerVO findById(@PathVariable("id") Long id) throws NotFoundException {
        Player player = playerService.findById(id);
        if(player == null){
            throw new NotFoundException();
        }

        return modelMapper.map(playerService.findById(id), PlayerVO.class);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping
    public ResponseEntity<List<PlayerVO>> findAll() {
        List<Player> players = playerService.findAll();
        List<PlayerVO> playerVOs = players.stream().map(player -> modelMapper.map(player, PlayerVO.class))
                .toList();
        return new ResponseEntity<>(playerVOs, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        playerService.delete(id);
    }
}
