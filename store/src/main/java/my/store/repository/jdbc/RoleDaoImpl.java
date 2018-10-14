package my.store.repository.jdbc;

import my.store.application.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Role role) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(role);
        transaction.commit();
        session.close();
    }

    @Override
    public Role get(Integer id) {
        Session session = sessionFactory.openSession();
        Role role = session.get(Role.class, id);
        session.close();
        return role;
    }

    @Override
    public Role update(Role role) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Role updRole = (Role) session.merge(role);
        transaction.commit();
        session.close();
        return updRole;
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
    public List<Role> getAll() {
        Session session = sessionFactory.openSession();
        List<Role> roles = session.createQuery("from Role").list();
        return roles;
    }
}
