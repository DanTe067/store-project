package my.flipside.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "flipgame", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlipGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private int gameId;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "user_id")
    private FlipUser jedy;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "user_id")
    private FlipUser syth;
    private int bet;
    private boolean completed;

    public FlipGame(FlipUser jedy, FlipUser syth, int bet) {
        this.jedy = jedy;
        this.syth = syth;
        this.bet = bet;
        this.completed = false;
    }

}
