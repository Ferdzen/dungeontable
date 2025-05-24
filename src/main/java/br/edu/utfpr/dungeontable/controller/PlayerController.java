package br.edu.utfpr.dungeontable.controller;

import br.edu.utfpr.dungeontable.model.table.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerController {
    public PlayerController() {}

    private List<Player> players = new ArrayList<>();

    @PostMapping
    public ResponseEntity<Player> save(@RequestBody Player player) {
        players.add(player);
        return new ResponseEntity<>(player, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Player update(@PathVariable("id") Integer id, @RequestBody Player player) {
        return player;
    }

    @GetMapping("/{id}")
    public Player findById(@PathVariable("id") Integer id) {
        return players.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    @GetMapping
    public ResponseEntity<List<Player>> list() {
        return new ResponseEntity<>(players, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public Player delete(@PathVariable("id") Integer id, @RequestBody Player player) {
        return player;
    }
}
