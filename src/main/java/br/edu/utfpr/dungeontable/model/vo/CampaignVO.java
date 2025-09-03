package br.edu.utfpr.dungeontable.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class CampaignVO {
    private Long id;
    private String name;
    private String description;
    @Schema(description = "ID do sistema utilizado na campanha")
    private Long systemCampaignId;
    @Schema(description = "ID do usuário dono da campanha")
    private Long userId;
    @Schema(description = "Lista de players que participam da campanha")
    private List<PlayerVO> players;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getSystemCampaignId() {
        return systemCampaignId;
    }

    public void setSystemCampaignId(Long systemCampaignId) {
        this.systemCampaignId = systemCampaignId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<PlayerVO> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerVO> players) {
        this.players = players;
    }
}
