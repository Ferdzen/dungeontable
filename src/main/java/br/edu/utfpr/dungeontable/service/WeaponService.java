package br.edu.utfpr.dungeontable.service;


import br.edu.utfpr.dungeontable.model.tools.Weapon;
import br.edu.utfpr.dungeontable.repository.WeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WeaponService {
    @Autowired
    private WeaponRepository weaponRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public Weapon save(Weapon weapon) {
        return weaponRepository.save(weapon);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Weapon update(Weapon weapon) {
        return weaponRepository.save(weapon);
    }

    public Weapon findById(Long id) {
        return weaponRepository.findById(id).orElse(null);
    }

    public List<Weapon> findAll() {
        return weaponRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        weaponRepository.deleteById(id);
    }
}
