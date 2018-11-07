package my.flipside.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "flipstat", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlipStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stat_id")
    private int statId;
    private int account;
    private int score;

    public FlipStat(int account, int score) {
        this.account = account;
        this.score = score;
    }

}
