package my.flipside.repository.dao.hibernate;

import my.flipside.application.model.FlipUser;
import my.flipside.repository.generic.GenericDao;

public interface UserDao extends GenericDao<Integer, FlipUser> {

    FlipUser getByLoginAndPassword(String login, String password);
}
