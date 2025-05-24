package br.edu.utfpr.dungeontable.controller;

import br.edu.utfpr.dungeontable.model.table.Campaign;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/campaign")
public class CampaignController {
    public CampaignController() {}

    private List<Campaign> campaigns = new ArrayList<>();

    @PostMapping
    public ResponseEntity<Campaign> save(@RequestBody Campaign campaign) {
        campaigns.add(campaign);
        return new ResponseEntity<>(campaign, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Campaign update(@PathVariable("id") Long id, @RequestBody Campaign campaign) {
        return campaign;
    }

    @GetMapping("/{id}")
    public Campaign findById(@PathVariable("id") Integer id) {
        return campaigns.stream().filter(campaign -> campaign.getId().equals(id)).findFirst().orElse(null);
    }

    @GetMapping
    public ResponseEntity<List<Campaign>> list() {
        return new ResponseEntity<>(campaigns, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public Campaign delete(@PathVariable("id") Integer id, @RequestBody Campaign campaign) {
        return campaign;
    }
}
