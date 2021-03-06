package my.flipside.repository.dao.hibernate;

import my.flipside.application.model.FlipGame;
import my.flipside.repository.generic.GameDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository(value = "gameDao")
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class GameDaoImpl implements GameDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int create(FlipGame game) {
        Session session = sessionFactory.getCurrentSession();
        session.save(game);
        return game.getGameId();
    }

    @Override
    public FlipGame get(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        FlipGame game = session.get(FlipGame.class, id);
        return game;
    }

    @Override
    public FlipGame update(FlipGame game) {
        Session session = sessionFactory.getCurrentSession();
        FlipGame updGame = (FlipGame) session.merge(game);
        return updGame;
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        FlipGame game = new FlipGame();
        game.setGameId(id);
        session.delete(game);
    }

    @Override
    public List<FlipGame> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<FlipGame> games = session.createQuery("from FlipGame").list();
        return games;
    }

    @Override
    public List<FlipGame> getByCompleted(Boolean completed) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from FlipGame where completed = :completed");
        query.setParameter("completed", completed);
        List<FlipGame> games = query.list();
        return games;
    }

}
