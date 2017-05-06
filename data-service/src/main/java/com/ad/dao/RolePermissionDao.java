package com.ad.dao;

import com.ad.dao.base.BaseDao;
import com.ad.entity.RolePermission;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by xiang on 2017/5/3.
 */
@Repository
public class RolePermissionDao extends BaseDao<RolePermission> {

    public void deleteByRoleId(Long roleId) {
        String hql = "delete from RolePermission where roleId=:roleId";
        Query query = getSession().createQuery(hql);
        query.setLong("roleId",roleId);
        query.executeUpdate();
    }
}
