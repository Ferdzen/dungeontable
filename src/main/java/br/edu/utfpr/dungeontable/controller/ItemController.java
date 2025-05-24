package br.edu.utfpr.dungeontable.controller;

import br.edu.utfpr.dungeontable.model.tools.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {
    public ItemController() {}

    private List<Item> items = new ArrayList<>();

    @PostMapping
    public ResponseEntity<Item> save(@RequestBody Item item) {
        items.add(item);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Item update(@PathVariable("id") Integer id, @RequestBody Item item)
    {
        return item;
    }

    @GetMapping("/{id}")
    public Item findById(@PathVariable("id") Integer id) {
        return items.stream().filter(item -> item.getId().equals(id)).findFirst().orElse(null);
    }

    @GetMapping
    public ResponseEntity<List<Item>> list() {
        return new ResponseEntity<>(items, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public Item delete(@PathVariable("id") Integer id, @RequestBody Item item) {
        return item;
    }
}
