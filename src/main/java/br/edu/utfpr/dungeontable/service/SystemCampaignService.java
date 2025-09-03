package br.edu.utfpr.dungeontable.service;

import br.edu.utfpr.dungeontable.model.table.SystemCampaign;
import br.edu.utfpr.dungeontable.repository.SystemCampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemCampaignService {
    @Autowired
    private SystemCampaignRepository systemCampaignRepository;

    public SystemCampaign findById(Long id){
        return systemCampaignRepository.findById(id).orElse(null);
    }

    public List<SystemCampaign> findAll(){
        return systemCampaignRepository.findAll();
    }
}
