package br.edu.utfpr.dungeontable.model.vo;

import java.util.List;

public class UserVO {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String password;

    private List<CampaignVO> campaigns;
    private List<PlayerVO> players;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<CampaignVO> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<CampaignVO> campaigns) {
        this.campaigns = campaigns;
    }

    public List<PlayerVO> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerVO> players) {
        this.players = players;
    }
}
