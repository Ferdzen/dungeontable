package br.edu.utfpr.dungeontable.model.table;

import jakarta.persistence.*;

@Entity
@Table(name = "DT_CAMPAIGN")
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PHONE")
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
