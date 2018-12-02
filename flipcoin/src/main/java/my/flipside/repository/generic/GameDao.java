package my.flipside.repository.generic;

import my.flipside.application.model.FlipGame;

import java.util.List;

public interface GameDao extends GenericDao<Integer, FlipGame> {

    List<FlipGame> getByCompleted(Boolean completed);
}
