package br.edu.utfpr.dungeontable.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CampaignVO {
    private Long id;
    private String name;
    private String description;
    private String systemCampaign;
    @Schema(description = "ID do usuário dono da campanha")
    private Long userId;

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

    public String getSystemCampaign() {
        return systemCampaign;
    }

    public void setSystemCampaign(String systemCampaign) {
        this.systemCampaign = systemCampaign;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
