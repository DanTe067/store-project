package my.flipside.repository.dao.hibernate;

import my.flipside.application.model.FlipStat;
import my.flipside.repository.generic.GenericDao;

public interface StatDao extends GenericDao<Integer, FlipStat> {
    @Override
    default void create(FlipStat stat) {

    }

    @Override
    default void delete(Integer id) {

    }
}
