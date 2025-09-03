package br.edu.utfpr.dungeontable.model.vo;

public class PlayerMagicVO {

    private Long id;

    private Long playerId;
    private Long magicId;
    private Long campaignId;

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public Long getMagicId() {
        return magicId;
    }

    public void setMagicId(Long magicId) {
        this.magicId = magicId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
