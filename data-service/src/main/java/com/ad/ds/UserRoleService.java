package com.ad.ds;

import com.ad.dao.UserRoleDao;
import com.ad.entity.Role;
import com.ad.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xiang on 2017/4/21.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    public void save(UserRole userRole) {
        userRoleDao.save(userRole);
    }

    public void save(Long userId, Long roleId) {
        userRoleDao.deleteById(userId);
        UserRole role = new UserRole();
        role.setCreateTime(System.currentTimeMillis());
        role.setUpdateTime(System.currentTimeMillis());
        role.setRoleId(roleId);
        role.setUserId(userId);
        userRoleDao.save(role);
    }

    public Role getByUserId(Long userId) {
        return userRoleDao.getByUserId(userId);
    }

}
