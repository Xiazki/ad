package com.ad.dao;

import com.ad.dao.base.BaseDao;
import com.ad.entity.Role;
import com.ad.entity.UserRole;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by xiang on 2017/4/21.
 */
@Repository
public class UserRoleDao extends BaseDao<UserRole> {

    public void deleteById(Long userId) {
        String hql = "delete from UserRole where userId =:userId";
        Query query = getSession().createQuery(hql);
        query.setLong("userId", userId);
        query.executeUpdate();
    }

    public Role getByUserId(Long userId) {
        String hql = "select r from Role r,UserRole ur where r.id = ur.roleId and ur.userId =:userId";
        Query query = getSession().createQuery(hql);
        query.setLong("userId", userId);
        return (Role) query.uniqueResult();
    }

}
