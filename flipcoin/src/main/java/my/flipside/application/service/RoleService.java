package my.flipside.application.service;

import my.flipside.application.model.FlipRole;
import my.flipside.repository.generic.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@EnableTransactionManagement
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int createRole(FlipRole role) {
        return roleDao.create(role);
    }

    public FlipRole getRole(Integer id) {
        return roleDao.get(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public FlipRole updateRole(FlipRole role) {
        return roleDao.update(role);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteRole(Integer id) {
        roleDao.delete(id);
    }

    public List<FlipRole> getAllRoles() {
        return roleDao.getAll();
    }
}
