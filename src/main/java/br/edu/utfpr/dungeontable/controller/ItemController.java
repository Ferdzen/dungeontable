package br.edu.utfpr.dungeontable.controller;

import br.edu.utfpr.dungeontable.exception.NotFoundException;
import br.edu.utfpr.dungeontable.model.tools.Item;
import br.edu.utfpr.dungeontable.model.tools.Magic;
import br.edu.utfpr.dungeontable.model.vo.ItemVO;
import br.edu.utfpr.dungeontable.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Get item by ID", description = "Returns a single item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404",  description = "Not found - The item was not found")
    })
    public ItemVO findById(@PathVariable("id") Long id) throws NotFoundException {
        Item item = itemService.findById(id);
        if(item == null){
            throw new NotFoundException();
        }
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
