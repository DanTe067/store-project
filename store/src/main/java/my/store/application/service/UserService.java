package my.store.application.service;

import my.store.application.model.User;
import my.store.repository.hibernate.UserDaoHibernateImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService {

    @Autowired
    private UserDaoHibernateImpl userDao;

    public void createUser(User user) {
        userDao.create(user);
    }

    public User getUser(Integer id) {
        return userDao.get(id);
    }

    public User updateUser(User user) {
        return userDao.update(user);
    }

    public void deleteUser(Integer id) {
        userDao.delete(id);
    }

    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    public List<User> getUsersByLogin(String login) {
        return userDao.getByLogin(login);
    }
}
