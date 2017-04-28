package com.ad.dao;

import com.ad.dao.base.BaseDao;
import com.ad.entity.Permission;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiang on 2017/4/20.
 */
@Repository
public class PermissionDao extends BaseDao<Permission> {

    public List<Permission> list() {
        String hql = "from Permission";
        Query query = getSession().createQuery(hql);
        return query.list();
    }

    public List<Permission> listByUserId(Long userId) {
        String hql = "select p from Permission p,UserPermission pu where p.id = pu.permissionId and pu.userId =:userId";
        Query query = getSession().createQuery(hql);
        query.setLong("userId", userId);
        return query.list();
    }

    public List<Permission> listByRole(Long roleId) {
        String hql = "select p from Permission p,RolePermission rp where p.id = rp.permissionId and rp.roleId =:roleId";
        Query query = getSession().createQuery(hql);
        query.setLong("roleId", roleId);
        return query.list();
    }
}
