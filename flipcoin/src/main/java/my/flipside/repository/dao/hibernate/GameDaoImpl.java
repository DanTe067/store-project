package my.flipside.repository.dao.hibernate;

import my.flipside.application.model.FlipGame;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository(value = "gameDao")
public class GameDaoImpl implements GameDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int create(FlipGame game) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(game);
        transaction.commit();
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

    @Override
    public FlipGame update(FlipGame game) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        FlipGame updGame = (FlipGame) session.merge(game);
        transaction.commit();
        session.close();
        return updGame;
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        FlipGame game = new FlipGame();
        game.setGameId(id);
        session.delete(game);
        transaction.commit();
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
