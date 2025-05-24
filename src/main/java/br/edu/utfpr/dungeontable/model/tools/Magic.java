package br.edu.utfpr.dungeontable.model.tools;


import jakarta.persistence.*;

@Entity
@Table(name = "DT_MAGIC")
public class Magic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SCHOOL_MAGIC")
    private String schoolMagic;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "LEVEL")
    private String level;

    @Column(name = "COMPONENTS")
    private String components;

    @Column(name = "CASTING_TIME")
    private String castingTime;

    @Column(name = "RANGE")
    private String range;

    @Column(name = "DURATION")
    private String duration;

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

    public String getSchoolMagic() {
        return schoolMagic;
    }

    public void setSchoolMagic(String schoolMagic) {
        this.schoolMagic = schoolMagic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getComponents() {
        return components;
    }

    public void setComponents(String components) {
        this.components = components;
    }

    public String getCastingTime() {
        return castingTime;
    }

    public void setCastingTime(String castingTime) {
        this.castingTime = castingTime;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
