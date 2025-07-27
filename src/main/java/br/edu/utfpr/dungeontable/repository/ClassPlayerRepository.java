package br.edu.utfpr.dungeontable.repository;

import br.edu.utfpr.dungeontable.model.table.ClassPlayer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassPlayerRepository extends JpaRepository<ClassPlayer,Long> {
}
