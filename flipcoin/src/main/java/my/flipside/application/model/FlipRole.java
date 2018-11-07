package my.flipside.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "fliprole", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlipRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleId;
    private String name;

    public FlipRole(String name) {
        this.name = name;
    }

}
