package my.flipside.application.service;

import my.flipside.application.model.FlipResult;
import my.flipside.repository.generic.ResultDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@EnableTransactionManagement
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class ResultService {

    @Autowired
    private ResultDao resultDao;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int createResult(FlipResult result) {
        return resultDao.create(result);
    }

    public FlipResult getResult(Integer id) {
        return resultDao.get(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public FlipResult updateResult(FlipResult result) {
        return resultDao.update(result);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteResult(Integer id) {
        resultDao.delete(id);
    }

    public List<FlipResult> getAllResults() {
        return resultDao.getAll();
    }

    public FlipResult getResultByGameId(Integer id) {
        return resultDao.getByGameId(id);
    }

    public List<FlipResult> getResultsByWinnerId(Integer id) {
        return resultDao.getByWinnerId(id);
    }
}
