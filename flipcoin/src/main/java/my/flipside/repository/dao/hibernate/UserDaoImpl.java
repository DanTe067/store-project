package my.flipside.repository.dao.hibernate;

import my.flipside.application.model.FlipUser;
import my.flipside.repository.generic.UserDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository(value = "userDao")
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int create(FlipUser user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
        return user.getUserId();
    }

    @Override
    public FlipUser get(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        FlipUser user = session.get(FlipUser.class, id);
        //session.close();
        return user;
    }

    @Override
    public FlipUser update(FlipUser user) {
        Session session = sessionFactory.getCurrentSession();
        FlipUser updUser = (FlipUser) session.merge(user);
        return updUser;
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        FlipUser user = new FlipUser();
        user.setUserId(id);
        session.delete(user);
    }

    @Override
    public List<FlipUser> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<FlipUser> users = session.createQuery("from FlipUser").list();
        return users;
    }

    @Override
    public FlipUser getByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from FlipUser where username = :username");
        query.setParameter("username", username);
        FlipUser user = (FlipUser) query.uniqueResult();
        return user;
    }

    @Override
    public FlipUser getByUsernameAndPassword(String username, String password) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from FlipUser where username = :username and password = :password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        FlipUser user = (FlipUser) query.uniqueResult();
        return user;
    }
}
