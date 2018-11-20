package my.flipside.application.service;

import my.flipside.application.model.FlipUser;
import my.flipside.repository.generic.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
@EnableTransactionManagement
public class UserService {

    @Autowired
    private UserDao userDao;

    public int createUser(FlipUser user) {
        return userDao.create(user);
    }

    public FlipUser getUser(Integer id) {
        return userDao.get(id);
    }

    public FlipUser updateUser(FlipUser user) {
        return userDao.update(user);
    }

    public void deleteUser(Integer id) {
        userDao.delete(id);
    }

    public List<FlipUser> getAllUsers() {
        return userDao.getAll();
    }

    public FlipUser getUserByUsername(String username) {
        return userDao.getByUsername(username);
    }

    public FlipUser getUserByUsernameAndPassword(String username, String password) {
        return userDao.getByUsernameAndPassword(username, password);
    }
}
