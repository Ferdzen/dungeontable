package br.edu.utfpr.dungeontable.repository;

import br.edu.utfpr.dungeontable.model.table.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player,Long> {
}
