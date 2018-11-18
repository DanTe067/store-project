package my.flipside.repository.dao.hibernate;

import my.flipside.application.model.FlipRole;
import my.flipside.repository.generic.RoleDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "roleDao")
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int create(FlipRole role) {
        Session session = sessionFactory.openSession();
        //Transaction transaction = session.beginTransaction();
        session.save(role);
        //transaction.commit();
        session.close();
        return role.getRoleId();
    }

    @Override
    public FlipRole get(Integer id) {
        Session session = sessionFactory.openSession();
        FlipRole role = session.get(FlipRole.class, id);
        session.close();
        return role;
    }

    @Override
    public FlipRole update(FlipRole role) {
        Session session = sessionFactory.openSession();
        //Transaction transaction = session.beginTransaction();
        FlipRole updRole = (FlipRole) session.merge(role);
        //transaction.commit();
        session.close();
        return updRole;
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        //Transaction transaction = session.beginTransaction();
        FlipRole role = new FlipRole();
        role.setRoleId(id);
        session.delete(role);
        //transaction.commit();
        session.close();
    }

    @Override
    public List<FlipRole> getAll() {
        Session session = sessionFactory.openSession();
        List<FlipRole> roles = session.createQuery("from FlipRole").list();
        return roles;
    }
}
