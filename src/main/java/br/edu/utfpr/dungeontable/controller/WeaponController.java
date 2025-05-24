package br.edu.utfpr.dungeontable.controller;

import br.edu.utfpr.dungeontable.model.tools.Weapon;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/weapon")
public class WeaponController {
    public WeaponController(){}

    private List<Weapon> weapons = new ArrayList<>();

    @PostMapping
    public ResponseEntity<Weapon> save(@RequestBody Weapon weapon) {
        weapons.add(weapon);
        return new ResponseEntity<>(weapon, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Weapon update(@PathVariable("id") Integer id, @RequestBody Weapon weapon)
    {
        return weapon;
    }

    @GetMapping("/{id}")
    public Weapon findById(@PathVariable("id") Integer id) {
        return weapons.stream().filter(weapon -> weapon.getId().equals(id)).findFirst().orElse(null);
    }

    @GetMapping
    public ResponseEntity<List<Weapon>> list() {
        return new ResponseEntity<>(weapons, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public Weapon delete(@PathVariable("id") Integer id, @RequestBody Weapon weapon) {
        return weapon;
    }
}
