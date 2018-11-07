package my.flipside.repository.dao.hibernate;

import my.flipside.application.model.FlipResult;
import my.flipside.repository.generic.GenericDao;

import java.util.List;

public interface ResultDao extends GenericDao<Integer, FlipResult> {

    FlipResult getByGameId(Integer id);

    List<FlipResult> getByWinnerId(Integer id);
}
