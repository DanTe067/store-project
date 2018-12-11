package my.flipside.repository.dao.hibernate;

import my.flipside.application.model.FlipRole;
import my.flipside.repository.generic.RoleDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository(value = "roleDao")
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int create(FlipRole role) {
        Session session = sessionFactory.getCurrentSession();
        session.save(role);
        return role.getRoleId();
    }

    @Override
    public FlipRole get(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        FlipRole role = session.get(FlipRole.class, id);
        //session.close();
        return role;
    }

    @Override
    public FlipRole update(FlipRole role) {
        Session session = sessionFactory.getCurrentSession();
        FlipRole updRole = (FlipRole) session.merge(role);
        return updRole;
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        FlipRole role = new FlipRole();
        role.setRoleId(id);
        session.delete(role);
    }

    @Override
    public List<FlipRole> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<FlipRole> roles = session.createQuery("from FlipRole").list();
        return roles;
    }
}
