package my.flipside.repository.dao.hibernate;

import my.flipside.application.model.FlipGame;
import my.flipside.repository.generic.GenericDao;

import java.util.List;

public interface GameDao extends GenericDao<Integer, FlipGame> {

    List<FlipGame> getByCreatorId(Integer id);
}
