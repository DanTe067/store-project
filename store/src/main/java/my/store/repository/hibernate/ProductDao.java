package my.store.repository.hibernate;

import my.store.application.model.Product;
import my.store.repository.generic.GenericDao;

import java.util.List;

public interface ProductDao extends GenericDao<Integer, Product> {

    Product getByName(String name);

    List<Product> getByPrice(int price);

    List<Product> getAllWithPriceLower(int maxPrice);

    List<Product> getAllWithPriceHigher(int minPrice);

    List<Product> getAllWithPriceBetween(int minPrice, int maxPrice);

}
