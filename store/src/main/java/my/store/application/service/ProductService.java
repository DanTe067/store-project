package my.store.application.service;

import my.store.application.model.Product;
import my.store.repository.hibernate.ProductDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDaoImpl productDao;

    public void createProduct(Product product) {
        productDao.create(product);
    }

    public Product getProduct(Integer id) {
        return productDao.get(id);
    }

    public Product updateProduct(Product product) {
        return productDao.update(product);
    }

    public void deleteProduct(Integer id) {
        productDao.delete(id);
    }

    public List<Product> getAllProducts() {
        return productDao.getAll();
    }

    public Product getProductsByName(String name) {
        return productDao.getByName(name);
    }

    public List<Product> getProductsByPrice(int price) {
        return productDao.getByPrice(price);
    }

    public List<Product> getAllProductsWithPriceLowerThan(int maxPrice) {
        return productDao.getAllWithPriceLower(maxPrice);
    }

    public List<Product> getAllProductsWithPriceHigherThan(int minPrice) {
        return productDao.getAllWithPriceLower(minPrice);
    }

    public List<Product> getAllProductsWithPriceBetween(int minPrice, int maxPrice) {
        return productDao.getAllWithPriceBetween(minPrice, maxPrice);
    }
}
