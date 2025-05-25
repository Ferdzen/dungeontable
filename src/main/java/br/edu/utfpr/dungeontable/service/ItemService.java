package br.edu.utfpr.dungeontable.service;

import br.edu.utfpr.dungeontable.model.tools.Item;
import br.edu.utfpr.dungeontable.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Item update(Item item) {
        return itemRepository.save(item);
    }

    public Item findById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        itemRepository.delete(id);
    }
}
