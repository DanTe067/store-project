package my.store.application.service;

import my.store.application.model.Role;
import my.store.repository.hibernate.RoleDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "roleService")
public class RoleService {

    @Autowired
    private RoleService roleService;

    @Autowired
    @Qualifier(value = "roleDao")
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
