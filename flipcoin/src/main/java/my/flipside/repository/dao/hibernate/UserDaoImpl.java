package my.flipside.repository.dao.hibernate;

import my.flipside.application.model.FlipUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository(value = "userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int create(FlipUser user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
        return user.getUserId();
    }

    @Override
    public FlipUser get(Integer id) {
        Session session = sessionFactory.openSession();
        FlipUser user = session.get(FlipUser.class, id);
        session.close();
        return user;
    }

    @Override
    public FlipUser update(FlipUser user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        FlipUser updUser = (FlipUser) session.merge(user);
        transaction.commit();
        session.close();
        return updUser;
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        FlipUser user = new FlipUser();
        user.setUserId(id);
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public List<FlipUser> getAll() {
        Session session = sessionFactory.openSession();
        List<FlipUser> users = session.createQuery("from FlipUser").list();
        return users;
    }

    @Override
    public FlipUser getByUsername(String username) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from FlipUser where username = :username");
        query.setParameter("username", username);
        FlipUser user = (FlipUser) query.getSingleResult();
        session.close();
        return user;
    }

    @Override
    public FlipUser getByUsernameAndPassword(String username, String password) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from FlipUser where username = :username and password = :password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        FlipUser user = (FlipUser) query.getSingleResult();
        session.close();
        return user;
    }
}
