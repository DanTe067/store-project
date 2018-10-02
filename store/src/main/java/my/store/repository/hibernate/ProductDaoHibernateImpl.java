package my.store.repository.hibernate;

import my.store.application.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Query;
import java.util.List;

public class ProductDaoHibernateImpl implements ProductDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(product);
        transaction.commit();
        session.close();
    }

    @Override
    public Product get(Integer id) {
        Session session = sessionFactory.openSession();
        Product product = session.get(Product.class, id);
        session.close();
        return product;
    }

    @Override
    public Product update(Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Product updProduct = (Product) session.merge(product);
        transaction.commit();
        session.close();
        return updProduct;
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(id);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Product> getAll() {
        Session session = sessionFactory.openSession();
        List<Product> products = session.createQuery("from Product").list();
        return products;
    }

    @Override
    public Product getByName(String name) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Product where name = ?");
        query.setParameter(0, name);
        Product product = (Product) query.getSingleResult();
        session.close();
        return product;
    }

    @Override
    public List<Product> getByPrice(int price) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Product where price = ?");
        query.setParameter(0, price);
        List<Product> products = query.getResultList();
        session.close();
        return products;
    }

    @Override
    public List<Product> getAllWithPriceLower(int maxPrice) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Product where price < ?");
        query.setParameter(0, maxPrice);
        List<Product> products = query.getResultList();
        session.close();
        return products;
    }

    @Override
    public List<Product> getAllWithPriceHigher(int minPrice) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Product where price > ?");
        query.setParameter(0, minPrice);
        List<Product> products = query.getResultList();
        session.close();
        return products;
    }

    @Override
    public List<Product> getAllWithPriveBetween(int minPrice, int maxPrice) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Product where price between ? and ?");
        query.setParameter(0, minPrice);
        query.setParameter(1, maxPrice);
        List<Product> products = query.getResultList();
        session.close();
        return products;
    }
}
