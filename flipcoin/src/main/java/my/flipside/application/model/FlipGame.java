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
    @JoinColumn(name = "jedi", referencedColumnName = "user_id")
    private FlipUser jedi;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "sith", referencedColumnName = "user_id")
    private FlipUser sith;
    private int bet;
    private boolean completed;

    public FlipGame(FlipUser jedi, FlipUser sith, int bet) {
        this.jedi = jedi;
        this.sith = sith;
        this.bet = bet;
        this.completed = false;
    }

}
