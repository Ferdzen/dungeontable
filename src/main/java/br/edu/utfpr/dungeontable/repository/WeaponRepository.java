package br.edu.utfpr.dungeontable.repository;

import br.edu.utfpr.dungeontable.model.tools.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeaponRepository extends JpaRepository<Weapon, Long> {
}
