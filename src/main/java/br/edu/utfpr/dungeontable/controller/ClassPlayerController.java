package br.edu.utfpr.dungeontable.controller;

import br.edu.utfpr.dungeontable.exception.NotFoundException;
import br.edu.utfpr.dungeontable.model.table.ClassPlayer;
import br.edu.utfpr.dungeontable.model.vo.ClassPlayerVO;
import br.edu.utfpr.dungeontable.service.ClassPlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/class")
public class ClassPlayerController {
    public ClassPlayerController(){}

    @Autowired
    private ClassPlayerService classPlayerService;

    @Autowired
    private ModelMapper modelMapper;

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("/{id}")
    @Operation(summary = "Get class of character by ID", description = "Returns a single class")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404",  description = "Not found - The class was not found")
    })
    public ClassPlayerVO findById(@PathVariable("id") Long id) throws NotFoundException{
        ClassPlayer classPlayer = classPlayerService.findById(id);
        if(classPlayer == null){
            throw new NotFoundException();
        }

        return modelMapper.map(classPlayerService.findById(id), ClassPlayerVO.class);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping
    public ResponseEntity<List<ClassPlayerVO>> findAll(){
        List<ClassPlayer> classPlayers = classPlayerService.findAll();
        List<ClassPlayerVO> classPlayerVOS = classPlayers.stream().map(classPlayer -> modelMapper.map(classPlayer, ClassPlayerVO.class)).toList();
        return new ResponseEntity<>(classPlayerVOS, HttpStatus.OK);
    }
}
