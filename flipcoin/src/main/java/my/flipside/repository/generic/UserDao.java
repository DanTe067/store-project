package my.flipside.repository.generic;

import my.flipside.application.model.FlipUser;

public interface UserDao extends GenericDao<Integer, FlipUser> {

    FlipUser getByUsername(String username);

    FlipUser getByUsernameAndPassword(String username, String password);
}
