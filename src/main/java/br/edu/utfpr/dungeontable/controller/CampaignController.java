package br.edu.utfpr.dungeontable.controller;

import br.edu.utfpr.dungeontable.exception.NotFoundException;
import br.edu.utfpr.dungeontable.model.User;
import br.edu.utfpr.dungeontable.model.table.Campaign;
import br.edu.utfpr.dungeontable.model.tools.Item;
import br.edu.utfpr.dungeontable.model.vo.CampaignVO;
import br.edu.utfpr.dungeontable.model.vo.PlayerVO;
import br.edu.utfpr.dungeontable.service.CampaignService;
import br.edu.utfpr.dungeontable.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/campaign")
public class CampaignController {
    public CampaignController() {}

    @Autowired
    private CampaignService campaignService;
    //private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @PostMapping
    public ResponseEntity<CampaignVO> save(@RequestBody CampaignVO campaignVO) {
        Campaign campaign = modelMapper.map(campaignVO,  Campaign.class);

        campaignService.save(campaign);
        campaignVO.setId(campaign.getId());
        return new ResponseEntity<>(campaignVO, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @PutMapping("/{id}")
    public ResponseEntity<CampaignVO> update(@PathVariable("id") Long id, @RequestBody CampaignVO campaignVO) {
        Campaign campaign = modelMapper.map(campaignVO,  Campaign.class);
        campaign.setId(id);
        campaignService.update(campaign);
        return new ResponseEntity<>(campaignVO, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("/{id}")
    @Operation(summary = "Get campaign by ID", description = "Returns a single campaign")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404",  description = "Not found - The campaign was not found")
    })
    public CampaignVO findById(@PathVariable("id") Long id) throws NotFoundException {
        Campaign campaign = campaignService.findById(id);
        if(campaign == null){
            throw new NotFoundException();
        }
        CampaignVO campaignVO = modelMapper.map(campaign, CampaignVO.class);

        List<PlayerVO> players = campaign.getPlayers().stream()
                .map(player -> modelMapper.map(player, PlayerVO.class))
                .toList();

        campaignVO.setPlayers(players);
        return campaignVO;
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping
    public ResponseEntity<List<CampaignVO>> findAll() {
        List<Campaign> campaigns = campaignService.findAll();

        List<CampaignVO> campaignVOs = campaigns.stream().map(campaign -> {
            CampaignVO vo = modelMapper.map(campaign, CampaignVO.class);
            List<PlayerVO> players = campaign.getPlayers().stream()
                    .map(player -> modelMapper.map(player, PlayerVO.class))
                    .toList();
            vo.setPlayers(players);
            return vo;
        }).toList();

        return new ResponseEntity<>(campaignVOs, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * */
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        campaignService.delete(id);
    }
}
