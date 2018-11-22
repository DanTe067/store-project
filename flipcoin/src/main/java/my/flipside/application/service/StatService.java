package my.flipside.application.service;

import my.flipside.application.model.FlipStat;
import my.flipside.repository.generic.StatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@EnableTransactionManagement
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class StatService {

    @Autowired
    private StatDao statDao;

    public FlipStat getStat(Integer id) {
        return statDao.get(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public FlipStat updateStat(FlipStat stat) {
        return statDao.update(stat);
    }

    public List<FlipStat> getAllStats() {
        return statDao.getAll();
    }
}
