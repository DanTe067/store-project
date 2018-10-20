package my.store.repository.hibernate;

import my.store.application.model.User;
import my.store.repository.generic.GenericDao;

import java.util.List;

public interface UserDao extends GenericDao<Integer, User> {

    List<User> getByLogin(String login);

}
