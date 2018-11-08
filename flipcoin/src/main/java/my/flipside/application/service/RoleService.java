package my.flipside.application.service;

import my.flipside.application.model.FlipRole;
import my.flipside.repository.dao.hibernate.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
