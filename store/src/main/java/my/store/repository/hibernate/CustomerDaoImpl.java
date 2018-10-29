package my.store.repository.hibernate;

import my.store.application.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository(value = "customerDao")
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        session.close();
    }

    @Override
    public Customer get(Integer id) {
        Session session = sessionFactory.openSession();
        Customer customer = session.get(Customer.class, id);
        session.close();
        return customer;
    }

    @Override
    public Customer update(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Customer updCustomer = (Customer) session.merge(customer);
        transaction.commit();
        session.close();
        return updCustomer;
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
    public List<Customer> getAll() {
        Session session = sessionFactory.openSession();
        List<Customer> customers = session.createQuery("from Customer").list();
        return customers;
    }

    @Override
    public List<Customer> getByName(String name) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Customer where name = ?");
        query.setParameter(0, name);
        List<Customer> customers = query.getResultList();
        session.close();
        return customers;
    }

    @Override
    public Customer getByEmail(String email) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Customer where email = ?");
        query.setParameter(0, email);
        Customer customer = (Customer) query.getSingleResult();
        session.close();
        return customer;
    }
}
