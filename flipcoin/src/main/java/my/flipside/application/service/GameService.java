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
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
@EnableTransactionManagement
public class GameService {

    @Autowired
    private GameDao gameDao;

    public int createGame(FlipGame game) {
        return gameDao.create(game);
    }

    public FlipGame getGame(Integer id) {
        return gameDao.get(id);
    }

    public FlipGame updateGame(FlipGame game) {
        return gameDao.update(game);
    }

    public void deleteGame(Integer id) {
        gameDao.delete(id);
    }

    public List<FlipGame> getAllGames() {
        return gameDao.getAll();
    }

    public List<FlipGame> getGamesByCreatorId(Integer id) {
        return gameDao.getByCreatorId(id);
    }
}
