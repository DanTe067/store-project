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
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
@EnableTransactionManagement
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public int createRole(FlipRole role) {
        return roleDao.create(role);
    }

    public FlipRole getRole(Integer id) {
        return roleDao.get(id);
    }

    public FlipRole updateRole(FlipRole role) {
        return roleDao.update(role);
    }

    public void deleteRole(Integer id) {
        roleDao.delete(id);
    }

    public List<FlipRole> getAllRoles() {
        return roleDao.getAll();
    }
}
