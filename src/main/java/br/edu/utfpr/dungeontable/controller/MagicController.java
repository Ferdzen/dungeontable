package br.edu.utfpr.dungeontable.controller;

import br.edu.utfpr.dungeontable.model.tools.Magic;
import br.edu.utfpr.dungeontable.model.vo.MagicVO;
import br.edu.utfpr.dungeontable.service.MagicService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/magic")
public class MagicController {
    public MagicController() {}

    @Autowired
    private MagicService magicService;

    private ModelMapper modelMapper = new ModelMapper();

    @PostMapping
    public ResponseEntity<MagicVO> save(@RequestBody MagicVO magicVO) {
        Magic magic = modelMapper.map(magicVO, Magic.class);
        magicService.save(magic);
        magicVO.setId(magic.getId());
        return new ResponseEntity<>(magicVO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MagicVO> update(@PathVariable("id") Long id, @RequestBody MagicVO magicVO) {
        Magic magic = modelMapper.map(magicVO, Magic.class);
        magic.setId(id);
        magicService.update(magic);
        return new ResponseEntity<>(magicVO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public MagicVO findById(@PathVariable("id") Long id) {
        return modelMapper.map(magicService.findById(id), MagicVO.class);
    }

    @GetMapping
    public ResponseEntity<List<MagicVO>> findAll() {
        List<Magic> magics = magicService.findAll();
        List<MagicVO> magicVOs = magics.stream().map(magic -> modelMapper.map(magic, MagicVO.class))
                .toList();
        return new ResponseEntity<>(magicVOs, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        magicService.delete(id);
    }

}
