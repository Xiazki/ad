package com.ad.ds;

import com.ad.dao.RolePermissionDao;
import com.ad.ds.exception.ParamException;
import com.ad.entity.Permission;
import com.ad.entity.RolePermission;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xiang on 2017/5/3.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RolePermissionService {

    @Autowired
    private RolePermissionDao rolePermissionDao;

    public void save(Long roleId, Long[] permissionIds) throws ParamException {
        rolePermissionDao.deleteByRoleId(roleId);
        if (permissionIds == null || permissionIds.length <= 0) {
            throw new ParamException("没有选择权限");
        }
        List<RolePermission> pers = Lists.newArrayList();
        for (Long p : permissionIds) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setPermissionId(p);
            rolePermission.setRoleId(roleId);
            rolePermission.setCreateTime(System.currentTimeMillis());
            pers.add(rolePermission);
        }
        rolePermissionDao.batchSave(pers);
    }
}
