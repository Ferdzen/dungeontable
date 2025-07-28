package br.edu.utfpr.dungeontable.controller;

import br.edu.utfpr.dungeontable.exception.NotFoundException;
import br.edu.utfpr.dungeontable.model.table.SystemCampaign;
import br.edu.utfpr.dungeontable.model.vo.SystemCampaignVO;
import br.edu.utfpr.dungeontable.service.SystemCampaignService;
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
@RequestMapping("/api/system_campaign")
public class SystemCampaignController {
    public SystemCampaignController(){}

    @Autowired
    private SystemCampaignService systemCampaignService;

    @Autowired
    private ModelMapper modelMapper;

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("/{id}")
    @Operation(summary = "Get system campaign by ID", description = "Returns a single system campaign")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404",  description = "Not found - The system campaign was not found")
    })
    public SystemCampaignVO findById(@PathVariable("id") Long id) throws NotFoundException{
        SystemCampaign systemCampaign = systemCampaignService.findById(id);
        if(systemCampaign == null){
            throw new NotFoundException();
        }

        return  modelMapper.map(systemCampaignService.findById(id), SystemCampaignVO.class);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping
    public ResponseEntity<List<SystemCampaignVO>> findAll(){
        List<SystemCampaign> systems = systemCampaignService.findAll();
        List<SystemCampaignVO> systemsVO = systems.stream().map(system -> modelMapper.map(system, SystemCampaignVO.class)).toList();
        return new ResponseEntity<>(systemsVO, HttpStatus.OK);

    }
}
