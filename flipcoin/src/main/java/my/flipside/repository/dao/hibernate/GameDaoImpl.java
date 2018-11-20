package my.flipside.repository.dao.hibernate;

import my.flipside.application.model.FlipGame;
import my.flipside.repository.generic.GameDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@EnableTransactionManagement
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
@Repository(value = "gameDao")
public class GameDaoImpl implements GameDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int create(FlipGame game) {
        Session session = sessionFactory.openSession();
        //Transaction transaction = session.beginTransaction();
        session.save(game);
        //transaction.commit();
        session.close();
        return game.getGameId();
    }

    @Override
    public FlipGame get(Integer id) {
        Session session = sessionFactory.openSession();
        FlipGame game = session.get(FlipGame.class, id);
        session.close();
        return game;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public FlipGame update(FlipGame game) {
        Session session = sessionFactory.openSession();
        //Transaction transaction = session.beginTransaction();
        FlipGame updGame = (FlipGame) session.merge(game);
        //transaction.commit();
        session.close();
        return updGame;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        //Transaction transaction = session.beginTransaction();
        FlipGame game = new FlipGame();
        game.setGameId(id);
        session.delete(game);
        //transaction.commit();
        session.close();
    }

    @Override
    public List<FlipGame> getAll() {
        Session session = sessionFactory.openSession();
        List<FlipGame> games = session.createQuery("from FlipGame").list();
        return games;
    }

    @Override
    public List<FlipGame> getByCreatorId(Integer id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from FlipGame where creator = :id");
        query.setParameter("id", id);
        List<FlipGame> games = query.getResultList();
        session.close();
        return games;
    }
}
