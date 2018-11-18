package my.flipside.application.service;

import my.flipside.application.model.FlipUser;
import my.flipside.repository.generic.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserService {

    @Autowired
    private UserDao userDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public int createUser(FlipUser user) {
        return userDao.create(user);
    }

    public FlipUser getUser(Integer id) {
        return userDao.get(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public FlipUser updateUser(FlipUser user) {
        return userDao.update(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
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
