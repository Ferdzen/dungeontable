package br.edu.utfpr.dungeontable.repository;

import br.edu.utfpr.dungeontable.model.interactions.PlayerMagic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerMagicRepository extends JpaRepository<PlayerMagic, Long> {

    List<PlayerMagic> findByPlayerId(Long playerId);

    List<PlayerMagic> findByPlayerIdAndCampaignId(Long playerId, Long campaignId);
}

