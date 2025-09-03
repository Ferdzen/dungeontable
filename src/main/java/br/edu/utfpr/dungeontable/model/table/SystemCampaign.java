package br.edu.utfpr.dungeontable.model.table;

import jakarta.persistence.*;

@Entity
@Table(name = "DT_SYSTEMS")
public class SystemCampaign {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME") // REQUIRED
    private String name;

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
}
