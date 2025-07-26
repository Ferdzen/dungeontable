package br.edu.utfpr.dungeontable.service;

import br.edu.utfpr.dungeontable.exception.BusinessException;
import br.edu.utfpr.dungeontable.exception.ErrorCode;
import br.edu.utfpr.dungeontable.model.tools.Item;
import br.edu.utfpr.dungeontable.repository.ItemRepository;
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
        if (item.getName() == null || item.getName().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "name item");
        } else if (item.getCategory() == null || item.getCategory().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "category item");
        } else if (item.getPrice() == null) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "price item");
        }
        return itemRepository.save(item);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Item update(Item item) {
        if (item.getId() == null) {
            throw new BusinessException(ErrorCode.ID_REQUIRED);
        } else if (item.getName() == null || item.getName().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "name item");
        } else if (item.getCategory() == null || item.getCategory().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "category item");
        } else if (item.getPrice() == null) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "price item");
        }
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
        if(id == null){
            throw new BusinessException(ErrorCode.ID_REQUIRED);
        }
        itemRepository.deleteById(id);
    }
}
