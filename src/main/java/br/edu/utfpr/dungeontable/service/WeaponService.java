package br.edu.utfpr.dungeontable.service;


import br.edu.utfpr.dungeontable.exception.BusinessException;
import br.edu.utfpr.dungeontable.exception.ErrorCode;
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
        if (weapon.getName() == null || weapon.getName().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "name weapon");
        } else if (weapon.getCategory() == null || weapon.getCategory().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "category weapon");
        } else if (weapon.getPrice() == null) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "price weapon");
        }
        return weaponRepository.save(weapon);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Weapon update(Weapon weapon) {
        if(weapon.getId() == null){
            throw new BusinessException(ErrorCode.ID_REQUIRED);
        }else if (weapon.getName() == null || weapon.getName().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "name weapon");
        } else if (weapon.getCategory() == null || weapon.getCategory().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "category weapon");
        } else if (weapon.getPrice() == null) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "price weapon");
        }
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
        if(id == null){
            throw new BusinessException(ErrorCode.ID_REQUIRED);
        }
        weaponRepository.deleteById(id);
    }
}
