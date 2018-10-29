package my.store.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "store_customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "customer_id")
    private int id;
    private String name;
    @Column(unique = true)
    private String email;
    /*@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;*/

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /*public Customer(String name, String email, List<Order> orders) {
        this.name = name;
        this.email = email;
        this.orders = orders;
    }*/

}
