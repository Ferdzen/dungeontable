package br.edu.utfpr.dungeontable.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;

public class ClassPlayerVO {
    private Long id;
    private String name;
    @Schema(description = "ID do sistema utilizado na campanha")
    private Long systemCampaignId;

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

    public Long getSystemCampaignId() {
        return systemCampaignId;
    }

    public void setSystemCampaignId(Long systemCampaignId) {
        this.systemCampaignId = systemCampaignId;
    }
}
