package my.store.application.service;

import my.store.application.model.Role;
import my.store.repository.jdbc.RoleDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleDaoImpl roleDao;

    public void createRole(Role role) {
        roleDao.create(role);
    }

    public Role getRole(Integer id) {
        return roleDao.get(id);
    }

    public Role updateRole(Role role) {
        return roleDao.update(role);
    }

    public void deleteRole(Integer id) {
        roleDao.delete(id);
    }

    public List<Role> getAllRoles() {
        return roleDao.getAll();
    }
}
