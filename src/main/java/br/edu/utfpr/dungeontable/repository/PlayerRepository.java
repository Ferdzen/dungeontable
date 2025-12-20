package br.edu.utfpr.dungeontable.repository;

import br.edu.utfpr.dungeontable.model.table.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player,Long> {
    List<Player> findByUserId(Long userId);
    List<Player> findByCampaignIdAndUserId(Long campaignId, Long userId);
}
