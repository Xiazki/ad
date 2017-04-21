package com.ad.ds;

import com.ad.dao.PermissionDao;
import com.ad.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xiang on 2017/4/20.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    public List<Permission> list() {
        return permissionDao.list();
    }

    public Permission get(Long id) {
        return permissionDao.get(id);
    }

    public void saveOrUpdate(Permission permission) {
        Permission per = new Permission();
        if (permission.getId() > 0) {
            per = permissionDao.get(permission.getId());
            per.setUpdateTime(System.currentTimeMillis());
        } else {
            per.setCreateTime(System.currentTimeMillis());
        }
        per.setPermissionDesc(permission.getPermissionDesc());
        per.setPermissionName(permission.getPermissionName());
        permissionDao.update(per);
    }

}
