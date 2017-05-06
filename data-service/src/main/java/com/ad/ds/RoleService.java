package com.ad.ds;

import com.ad.dao.RoleDao;
import com.ad.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xiang on 2017/5/3.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public List<Role> list() {
        return roleDao.list();
    }

    public Role getById(Long id) {
        return roleDao.get(id);
    }

    public void delete(Long id) {
        roleDao.delete(id);
    }

    public void saveOrUpdate(Role role) {
        Role newRole = new Role();
        if (role.getId() > 0) {
            newRole = roleDao.get(role.getId());
            newRole.setUpdateTime(System.currentTimeMillis());
        } else {
            newRole.setCreateTime(System.currentTimeMillis());
        }
        newRole.setRoleName(role.getRoleName());
        newRole.setRoleDesc(role.getRoleDesc());
        roleDao.update(newRole);
    }
}
