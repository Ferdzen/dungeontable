package br.edu.utfpr.dungeontable.repository;

import br.edu.utfpr.dungeontable.model.interactions.PlayerWeapon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerWeaponRepository extends JpaRepository<PlayerWeapon, Long> {

    List<PlayerWeapon> findByPlayerId(Long playerId);

    List<PlayerWeapon> findByPlayerIdAndCampaignId(Long playerId, Long campaignId);
}
