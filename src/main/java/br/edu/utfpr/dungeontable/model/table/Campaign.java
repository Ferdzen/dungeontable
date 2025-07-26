package br.edu.utfpr.dungeontable.model.table;

import br.edu.utfpr.dungeontable.model.User;
import jakarta.persistence.*;

@Entity
@Table(name = "DT_CAMPAIGN")
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME") // REQUIRED
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "SYSTEM_CAMPAIGN")
    private String systemCampaign;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
