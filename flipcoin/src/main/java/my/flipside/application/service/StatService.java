package my.flipside.application.service;

import my.flipside.application.model.FlipStat;
import my.flipside.repository.generic.StatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class StatService {

    @Autowired
    private StatDao statDao;

    public FlipStat getStat(Integer id) {
        return statDao.get(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public FlipStat updateStat(FlipStat stat) {
        return statDao.update(stat);
    }

    public List<FlipStat> getAllStats() {
        return statDao.getAll();
    }
}