package my.flipside.repository.generic;

import my.flipside.application.model.FlipStat;

public interface StatDao extends GenericDao<Integer, FlipStat> {
    @Override
    default int create(FlipStat stat) {
        return 0;
    }

    @Override
    default void delete(Integer id) {

    }
}
