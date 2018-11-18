package my.flipside.repository.dao.hibernate;

import my.flipside.application.model.FlipStat;
import my.flipside.repository.generic.StatDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "statDao")
public class StatDaoImpl implements StatDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public FlipStat get(Integer id) {
        Session session = sessionFactory.openSession();
        FlipStat stat = session.get(FlipStat.class, id);
        session.close();
        return stat;
    }

    @Override
    public FlipStat update(FlipStat stat) {
        Session session = sessionFactory.openSession();
        //Transaction transaction = session.beginTransaction();
        FlipStat updStat = (FlipStat) session.merge(stat);
        //transaction.commit();
        session.close();
        return updStat;
    }

    @Override
    public List<FlipStat> getAll() {
        Session session = sessionFactory.openSession();
        List<FlipStat> stats = session.createQuery("from FlipStat").list();
        return stats;
    }
}