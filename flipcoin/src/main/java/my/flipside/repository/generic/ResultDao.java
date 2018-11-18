package my.flipside.repository.generic;

import my.flipside.application.model.FlipResult;

import java.util.List;

public interface ResultDao extends GenericDao<Integer, FlipResult> {

    FlipResult getByGameId(Integer id);

    List<FlipResult> getByWinnerId(Integer id);
}
