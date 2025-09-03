package br.edu.utfpr.dungeontable.model.interactions;

import br.edu.utfpr.dungeontable.model.table.Campaign;
import br.edu.utfpr.dungeontable.model.table.Player;
import br.edu.utfpr.dungeontable.model.tools.Item;

import jakarta.persistence.*;
@Entity
@Table(name = "PLAYER_ITEM")
public class PlayerItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PLAYER_ID", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID", nullable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "CAMPAIGN_ID", nullable = false)
    private Campaign campaign;

    @Column(name = "QUANTITY")
    private Integer quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
