package br.edu.utfpr.dungeontable.model.table;

public class Campaign {
    private Integer id;
    private String name;
    private String description;
    private String systemCampaign;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
