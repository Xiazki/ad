package com.ad.ds;

import com.ad.dao.UserPermissionDao;
import com.ad.entity.UserPermission;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xiang on 2017/4/21.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserPermissionService {

    @Autowired
    private UserPermissionDao userPermissionDao;

    public void save(Long userId, List<Long> permissionIds) {
        List<UserPermission> userPermissions = Lists.newArrayList();
        for (Long pId : permissionIds) {
            UserPermission userPermission = new UserPermission();
            userPermission.setUserId(userId);
            userPermission.setPermissionId(pId);
            userPermission.setUpdateTime(System.currentTimeMillis());
            userPermission.setCreateTime(System.currentTimeMillis());
            userPermissions.add(userPermission);
        }
        userPermissionDao.batchSave(userPermissions);
    }
}
