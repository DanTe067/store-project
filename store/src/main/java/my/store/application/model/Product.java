package my.store.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "store_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;
    private String name;
    private int price;
    private String image;

    public Product(String name, int price, String image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

}
