package br.edu.utfpr.dungeontable.repository;

import br.edu.utfpr.dungeontable.model.interactions.PlayerItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerItemRepository extends JpaRepository<PlayerItem, Long> {

    List<PlayerItem> findByPlayerId(Long playerId);

    List<PlayerItem> findByPlayerIdAndCampaignId(Long playerId, Long campaignId);
}

