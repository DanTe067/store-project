package my.flipside.application.service;

import my.flipside.application.model.FlipRole;
import my.flipside.repository.generic.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public int createRole(FlipRole role) {
        return roleDao.create(role);
    }

    public FlipRole getRole(Integer id) {
        return roleDao.get(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public FlipRole updateRole(FlipRole role) {
        return roleDao.update(role);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteRole(Integer id) {
        roleDao.delete(id);
    }

    public List<FlipRole> getAllRoles() {
        return roleDao.getAll();
    }
}
