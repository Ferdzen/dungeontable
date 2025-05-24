package br.edu.utfpr.dungeontable.controller;

import br.edu.utfpr.dungeontable.model.tools.Magic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/magic")
public class MagicController {
    public MagicController() {}

    private List<Magic> magics = new ArrayList<>();

    @PostMapping
    public ResponseEntity<Magic> save(@RequestBody Magic magic) {
        magics.add(magic);
        return new ResponseEntity<>(magic, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Magic update(@PathVariable("id") Integer id, @RequestBody Magic magic) {
        return magics.set(id, magic);
    }

    @GetMapping("/{id}")
    public Magic findById(@PathVariable("id") Integer id) {
        return magics.stream().filter(magic -> magic.getId().equals(id)).findFirst().orElse(null);
    }

    @GetMapping
    public ResponseEntity<List<Magic>> list() {
        return new ResponseEntity<>(magics, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public Magic delete(@PathVariable("id") Integer id,  @RequestBody Magic magic) {
        return magic;
    }

}
