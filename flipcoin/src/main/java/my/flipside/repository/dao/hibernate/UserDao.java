package my.flipside.repository.dao.hibernate;

import my.flipside.application.model.FlipUser;
import my.flipside.repository.generic.GenericDao;

public interface UserDao extends GenericDao<Integer, FlipUser> {

    FlipUser getByUsername(String username);

    FlipUser getByUsernameAndPassword(String username, String password);
}
