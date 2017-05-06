package com.ad.dao;

import com.ad.dao.base.BaseDao;
import com.ad.entity.UserPermission;
import com.ad.entity.base.BaseEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by xiang on 2017/4/21.
 */
@Repository
public class UserPermissionDao extends BaseDao<UserPermission> {

    public void deleteByUserId(Long userId) {
        String hql = "delete from UserPermission where userId=:userId";
        Query query = getSession().createQuery(hql);
        query.setLong("userId", userId);
        query.executeUpdate();
    }
}
