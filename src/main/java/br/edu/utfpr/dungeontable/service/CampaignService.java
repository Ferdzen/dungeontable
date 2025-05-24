package br.edu.utfpr.dungeontable.service;

import br.edu.utfpr.dungeontable.model.table.Campaign;
import br.edu.utfpr.dungeontable.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CampaignService {
    @Autowired
    private CampaignRepository campaignRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public Campaign save(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Campaign update(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    public Campaign findById(Long id) {
        return campaignRepository.findById(id).orElse(null);
    }

    public List<Campaign> findAll() {
        return campaignRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        campaignRepository.deleteById(id);
    }
}
