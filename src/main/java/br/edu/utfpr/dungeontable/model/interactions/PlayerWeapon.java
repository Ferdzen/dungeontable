package br.edu.utfpr.dungeontable.model.interactions;
import br.edu.utfpr.dungeontable.model.table.Campaign;
import br.edu.utfpr.dungeontable.model.table.Player;
import br.edu.utfpr.dungeontable.model.tools.Weapon;
import jakarta.persistence.*;


@Entity
@Table(name = "PLAYER_WEAPON")
public class PlayerWeapon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PLAYER_ID", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "WEAPON_ID", nullable = false)
    private Weapon weapon;

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

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
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

