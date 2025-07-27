package br.edu.utfpr.dungeontable.model.interactions;
import br.edu.utfpr.dungeontable.model.table.Campaign;
import br.edu.utfpr.dungeontable.model.table.Player;
import br.edu.utfpr.dungeontable.model.tools.Magic;
import jakarta.persistence.*;

@Entity
@Table(name = "PLAYER_MAGIC")
public class PlayerMagic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PLAYER_ID", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "MAGIC_ID", nullable = false)
    private Magic magic;

    @ManyToOne
    @JoinColumn(name = "CAMPAIGN_ID", nullable = false)
    private Campaign campaign;

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

    public Magic getMagic() {
        return magic;
    }

    public void setMagic(Magic magic) {
        this.magic = magic;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }
}

