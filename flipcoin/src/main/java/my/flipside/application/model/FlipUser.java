package my.flipside.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "flipuser", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlipUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;
    private String username;
    private String password;
    private String email;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "stat_id", referencedColumnName = "stat_id")
    private FlipStat stat;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private FlipRole role;

    public FlipUser(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
