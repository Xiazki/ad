package com.ad.dao;

import com.ad.dao.base.BaseDao;
import com.ad.entity.Role;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiang on 2017/5/3.
 */
@Repository
public class RoleDao extends BaseDao<Role> {

    public List<Role> list() {
        String hql = " from Role";
        Query query = getSession().createQuery(hql);
        return query.list();
    }
}
