package br.edu.utfpr.dungeontable.model.table;

import br.edu.utfpr.dungeontable.model.User;
import jakarta.persistence.*;

@Entity
@Table(name = "DT_PLAYER")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME_PLAYER") // REQUIRED
    private String namePlayer;
    @Column(name = "NAME_CHARACTER") // REQUIRED
    private String nameCharacter;
    @Column(name = "DESCRIPTION_CHARACTER")
    private String descriptionCharacter;

    @Lob
    @Column(name = "BACKGROUND", columnDefinition = "LONGTEXT")
    private String background;
    @Column(name = "ANTECEDENT") // REQUIRED
    private String antecedent;
    @Column(name = "RACE_CHARACTER") // REQUIRED
    private String raceCharacter;
    @Column(name = "AGE") // REQUIRED
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "CLASSPLAYER_ID") // REQUIRED
    private ClassPlayer classPlayer;

    @ManyToOne
    @JoinColumn(name = "USER_ID") // REQUIRED
    private User user;

    @ManyToOne
    @JoinColumn(name = "CAMPAIGN_ID")
    private Campaign campaign;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public String getNameCharacter() {
        return nameCharacter;
    }

    public void setNameCharacter(String nameCharacter) {
        this.nameCharacter = nameCharacter;
    }

    public String getDescriptionCharacter() {
        return descriptionCharacter;
    }

    public void setDescriptionCharacter(String descriptionCharacter) {
        this.descriptionCharacter = descriptionCharacter;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getAntecedent() {
        return antecedent;
    }

    public void setAntecedent(String antecedent) {
        this.antecedent = antecedent;
    }

    public String getRaceCharacter() {
        return raceCharacter;
    }

    public void setRaceCharacter(String raceCharacter) {
        this.raceCharacter = raceCharacter;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public ClassPlayer getClassPlayer() {
        return classPlayer;
    }

    public void setClassPlayer(ClassPlayer classPlayer) {
        this.classPlayer = classPlayer;
    }
}
