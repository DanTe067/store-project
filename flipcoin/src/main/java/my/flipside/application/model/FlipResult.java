package my.flipside.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "flipresult", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlipResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private int resultId;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id", referencedColumnName = "game_id")
    private FlipGame game;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "winner", referencedColumnName = "user_id")
    private FlipUser winner;
    private LocalDate date;

    public FlipResult(FlipGame game, FlipUser winner) {
        this.game = game;
        this.winner = winner;
    }

}
