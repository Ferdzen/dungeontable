package br.edu.utfpr.dungeontable.controller;

import br.edu.utfpr.dungeontable.model.tools.Item;
import br.edu.utfpr.dungeontable.model.vo.ItemVO;
import br.edu.utfpr.dungeontable.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {
    public ItemController() {}

    @Autowired
    private ItemService itemService;
    private ModelMapper modelMapper = new ModelMapper();


    @PostMapping
    public ResponseEntity<ItemVO> save(@RequestBody ItemVO itemVO) {
        Item item = modelMapper.map(itemVO, Item.class);
        itemService.save(item);
        itemVO.setId(item.getId());
        return new ResponseEntity<>(itemVO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemVO> update(@PathVariable("id") Long id, @RequestBody ItemVO itemVO)
    {
        Item item = modelMapper.map(itemVO, Item.class);
        item.setId(id);
        itemService.update(item);
        return new ResponseEntity<>(itemVO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ItemVO findById(@PathVariable("id") Long id) {
        return modelMapper.map(itemService.findById(id), ItemVO.class);
    }

    @GetMapping
    public ResponseEntity<List<ItemVO>> findAll() {
        List<Item> items = itemService.findAll();
        List<ItemVO> itemVOs = items.stream().map(item -> modelMapper.map(item, ItemVO.class)).toList();
        return new ResponseEntity<>(itemVOs, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        itemService.delete(id);
    }
}
