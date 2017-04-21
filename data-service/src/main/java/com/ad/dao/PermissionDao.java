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
}
