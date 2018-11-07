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
    private String login;
    private String password;
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stat_id", referencedColumnName = "stat_id")
    private FlipStat stat;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private FlipRole role;

    public FlipUser(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

}
