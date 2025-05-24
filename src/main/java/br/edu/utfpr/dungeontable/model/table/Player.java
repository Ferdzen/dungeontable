package br.edu.utfpr.dungeontable.model.table;

import jakarta.persistence.*;

@Entity
@Table(name = "DT_PLAYER")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "NAME_PLAYER")
    private String namePlayer;
    @Column(name = "NAME_CHARACTER")
    private String nameCharacter;
    @Column(name = "DESCRIPTION_CHARACTER")
    private String descriptionCharacter;
    @Column(name = "CLASS_CHARACTER")
    private String classCharacter;
    @Column(name = "BACKGROUND")
    private String background;
    @Column(name = "ANTECEDENT")
    private String antecedent;
    @Column(name = "RACE_CHARACTER")
    private String raceCharacter;
    @Column(name = "AGE")
    private Integer age;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getClassCharacter() {
        return classCharacter;
    }

    public void setClassCharacter(String classCharacter) {
        this.classCharacter = classCharacter;
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
}
