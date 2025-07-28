package br.edu.utfpr.dungeontable.controller;

import br.edu.utfpr.dungeontable.exception.NotFoundException;
import br.edu.utfpr.dungeontable.model.table.Player;
import br.edu.utfpr.dungeontable.model.tools.Weapon;
import br.edu.utfpr.dungeontable.model.vo.WeaponVO;
import br.edu.utfpr.dungeontable.service.WeaponService;
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
@RequestMapping("/api/weapon")
public class WeaponController {
    public WeaponController(){}

    @Autowired
    private WeaponService weaponService;
    @Autowired
    private ModelMapper modelMapper;

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @PostMapping
    public ResponseEntity<WeaponVO> save(@RequestBody WeaponVO weaponVO) {
        Weapon weapon = modelMapper.map(weaponVO, Weapon.class);
        weaponService.save(weapon);
        weaponVO.setId(weapon.getId());
        return new ResponseEntity<>(weaponVO, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @PutMapping("/{id}")
    public ResponseEntity<WeaponVO> update(@PathVariable("id") Long id, @RequestBody WeaponVO weaponVO)
    {
        Weapon weapon = modelMapper.map(weaponVO, Weapon.class);
        weapon.setId(id);
        weaponService.update(weapon);
        return new ResponseEntity<>(weaponVO, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("/{id}")
    @Operation(summary = "Get weapon by ID", description = "Returns a single weapon")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404",  description = "Not found - The weapon was not found")
    })
    public WeaponVO findById(@PathVariable("id") Long id) throws NotFoundException {
        Weapon weapon = weaponService.findById(id);
        if(weapon == null){
            throw new NotFoundException();
        }
        return modelMapper.map(weaponService.findById(id), WeaponVO.class);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping
    public ResponseEntity<List<WeaponVO>> findAll() {
        List<Weapon> weapons = weaponService.findAll();
        List<WeaponVO> weaponVOS = weapons.stream().map(weapon -> modelMapper.map(weapon, WeaponVO.class))
                .toList();
        return new ResponseEntity<>(weaponVOS, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        weaponService.delete(id);
    }
}
