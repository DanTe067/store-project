package my.flipside.application.service;

import my.flipside.application.model.FlipStat;
import my.flipside.repository.dao.hibernate.StatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatService {

    @Autowired
    private StatDao statDao;

    public FlipStat getStat(Integer id) {
        return statDao.get(id);
    }

    public FlipStat updateStat(FlipStat stat) {
        return statDao.update(stat);
    }

    public List<FlipStat> getAllStats() {
        return statDao.getAll();
    }
}
