package my.flipside.repository.dao.hibernate;

import my.flipside.application.model.FlipResult;
import my.flipside.repository.generic.ResultDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository(value = "resultDao")
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class ResultDaoImpl implements ResultDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int create(FlipResult result) {
        Session session = sessionFactory.getCurrentSession();
        //Transaction transaction = session.beginTransaction();
        session.save(result);
        //transaction.commit();
        //session.close();
        return result.getResultId();
    }

    @Override
    public FlipResult get(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        FlipResult result = session.get(FlipResult.class, id);
        //session.close();
        return result;
    }

    @Override
    public FlipResult update(FlipResult result) {
        Session session = sessionFactory.getCurrentSession();
        //Transaction transaction = session.beginTransaction();
        FlipResult updResult = (FlipResult) session.merge(result);
        //transaction.commit();
        //session.close();
        return updResult;
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        //Transaction transaction = session.beginTransaction();
        FlipResult result = new FlipResult();
        result.setResultId(id);
        session.delete(result);
        //transaction.commit();
        //session.close();
    }

    @Override
    public List<FlipResult> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<FlipResult> results = session.createQuery("from FlipResult").list();
        return results;
    }

    @Override
    public FlipResult getByGameId(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from FlipResult where game = :id");
        query.setParameter("id", id);
        FlipResult result = (FlipResult) query.uniqueResult();
        //session.close();
        return result;
    }

    @Override
    public List<FlipResult> getByWinnerId(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from FlipResult where winner = :id");
        query.setParameter("id", id);
        List<FlipResult> results = query.list();
        //session.close();
        return results;
    }
}
