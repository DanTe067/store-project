package my.flipside.application.service;

import my.flipside.application.model.FlipResult;
import my.flipside.repository.dao.hibernate.ResultDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {

    @Autowired
    private ResultDao resultDao;

    public int createResult(FlipResult result) {
        return resultDao.create(result);
    }

    public FlipResult getResult(Integer id) {
        return resultDao.get(id);
    }

    public FlipResult updateResult(FlipResult result) {
        return resultDao.update(result);
    }

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
