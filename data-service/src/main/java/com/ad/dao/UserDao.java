package com.ad.dao;

import com.ad.dao.base.BaseDao;
import com.ad.entity.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by xiang on 2017/4/7.
 */
@Repository
public class UserDao extends BaseDao<User> {

    public User getUserByName(String username) {
        String hql = "from User u where u.username=:username";
        Query query = getSession().createQuery(hql);
        query.setString("username",username);
        return (User) query.uniqueResult();
    }
}
