package my.flipside.application.service;

import my.flipside.application.model.FlipUser;
import my.flipside.repository.dao.hibernate.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    public FlipUser getUserByLoginAndPassword(String login, String password) {
        return userDao.getByLoginAndPassword(login, password);
    }
}
