package br.edu.utfpr.dungeontable.repository;

import br.edu.utfpr.dungeontable.model.table.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign,Long> {
}
