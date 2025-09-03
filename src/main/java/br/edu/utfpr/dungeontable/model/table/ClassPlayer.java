package br.edu.utfpr.dungeontable.model.table;

import jakarta.persistence.*;

@Entity
@Table(name = "DT_CLASS")
public class ClassPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "SYSTEMCAMPAIGN_ID")
    private SystemCampaign systemCampaign;

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

    public SystemCampaign getSystemCampaign() {
        return systemCampaign;
    }

    public void setSystemCampaign(SystemCampaign systemCampaign) {
        this.systemCampaign = systemCampaign;
    }
}
