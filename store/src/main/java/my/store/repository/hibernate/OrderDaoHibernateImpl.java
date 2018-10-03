package my.store.repository.hibernate;

import my.store.application.model.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class OrderDaoHibernateImpl implements OrderDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Order order) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(order);
        transaction.commit();
        session.close();
    }

    @Override
    public Order get(Integer id) {
        Session session = sessionFactory.openSession();
        Order order = session.get(Order.class, id);
        session.close();
        return order;
    }

    @Override
    public Order update(Order order) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Order updOrder = (Order) session.merge(order);
        transaction.commit();
        session.close();
        return updOrder;
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
    public List<Order> getAll() {
        Session session = sessionFactory.openSession();
        List<Order> orders = session.createQuery("from Order").list();
        return orders;
    }

    @Override
    public List<Order> getByCustomerId(int id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Order where customer = ?");
        query.setParameter(0, id);
        List<Order> orders = query.getResultList();
        session.close();
        return orders;
    }
}
