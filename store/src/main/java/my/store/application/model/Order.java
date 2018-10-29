package my.store.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
//import java.util.List;

@Entity
@Table(name = "store_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    /*@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "store_order_product",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    )
    private List<Product> products;*/
    private String products;
    private int price;
    private Date date;

    public Order(String products, Customer customer, int price) {
        this.products = products;
        this.customer = customer;
        this.price = price;
    }

}
