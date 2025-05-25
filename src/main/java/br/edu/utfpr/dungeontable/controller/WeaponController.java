package br.edu.utfpr.dungeontable.controller;

import br.edu.utfpr.dungeontable.model.tools.Weapon;
import br.edu.utfpr.dungeontable.model.vo.WeaponVO;
import br.edu.utfpr.dungeontable.service.WeaponService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/weapon")
public class WeaponController {
    public WeaponController(){}

    @Autowired
    private WeaponService weaponService;
    private ModelMapper modelMapper = new ModelMapper();

    @PostMapping
    public ResponseEntity<WeaponVO> save(@RequestBody WeaponVO weaponVO) {
        Weapon weapon = modelMapper.map(weaponVO, Weapon.class);
        weaponService.save(weapon);
        weaponVO.setId(weapon.getId());
        return new ResponseEntity<>(weaponVO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WeaponVO> update(@PathVariable("id") Long id, @RequestBody WeaponVO weaponVO)
    {
        Weapon weapon = modelMapper.map(weaponVO, Weapon.class);
        weapon.setId(id);
        weaponService.update(weapon);
        return new ResponseEntity<>(weaponVO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public WeaponVO findById(@PathVariable("id") Long id) {
        return modelMapper.map(weaponService.findById(id), WeaponVO.class);
    }

    @GetMapping
    public ResponseEntity<List<WeaponVO>> findAll() {
        List<Weapon> weapons = weaponService.findAll();
        List<WeaponVO> weaponVOS = weapons.stream().map(weapon -> modelMapper.map(weapon, WeaponVO.class))
                .toList();
        return new ResponseEntity<>(weaponVOS, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        weaponService.delete(id);
    }
}
