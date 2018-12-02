package my.flipside.application.service;

import my.flipside.application.model.FlipGame;
import my.flipside.repository.generic.GameDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@EnableTransactionManagement
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class GameService {

    @Autowired
    private GameDao gameDao;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int createGame(FlipGame game) {
        return gameDao.create(game);
    }

    public FlipGame getGame(Integer id) {
        return gameDao.get(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public FlipGame updateGame(FlipGame game) {
        return gameDao.update(game);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteGame(Integer id) {
        gameDao.delete(id);
    }

    public List<FlipGame> getAllGames() {
        return gameDao.getAll();
    }

    public List<FlipGame> getGamesByCompleted(Boolean completed) {
        return gameDao.getByCompleted(completed);
    }

}
