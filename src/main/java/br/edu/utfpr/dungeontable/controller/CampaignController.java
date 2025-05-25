package br.edu.utfpr.dungeontable.controller;

import br.edu.utfpr.dungeontable.model.table.Campaign;
import br.edu.utfpr.dungeontable.model.vo.CampaignVO;
import br.edu.utfpr.dungeontable.service.CampaignService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/campaign")
public class CampaignController {
    public CampaignController() {}

    @Autowired
    private CampaignService campaignService;

    private ModelMapper modelMapper = new ModelMapper();


    @PostMapping
    public ResponseEntity<CampaignVO> save(@RequestBody CampaignVO campaignVO) {
        Campaign campaign = modelMapper.map(campaignVO,  Campaign.class);
        campaignService.save(campaign);
        campaignVO.setId(campaign.getId());
        return new ResponseEntity<>(campaignVO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampaignVO> update(@PathVariable("id") Long id, @RequestBody CampaignVO campaignVO) {
        Campaign campaign = modelMapper.map(campaignVO,  Campaign.class);
        campaign.setId(id);
        campaignService.update(campaign);
        return new ResponseEntity<>(campaignVO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public CampaignVO findById(@PathVariable("id") Long id) {
        return modelMapper.map(campaignService.findById(id), CampaignVO.class);
    }

    @GetMapping
    public ResponseEntity<List<CampaignVO>> findAll() {
        List<Campaign> campaigns = campaignService.findAll();
        List<CampaignVO> campaignVOs = campaigns.stream().map(campaign ->
                modelMapper.map(campaign, CampaignVO.class)).toList();;
        return new ResponseEntity<>(campaignVOs, HttpStatus.CREATED);
    }

    /**
     *
     * @param id
     * */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        campaignService.delete(id);
    }
}
