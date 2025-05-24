package br.edu.utfpr.dungeontable.repository;

import br.edu.utfpr.dungeontable.model.tools.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Integer> {
}
