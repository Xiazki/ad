package com.ad.dao;

import com.ad.dao.base.BaseDao;
import com.ad.entity.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiang on 2017/4/7.
 */
@Repository
public class UserDao extends BaseDao<User> {

    public User getUserByName(String username) {
        String hql = "from User u where u.username=:username";
        Query query = getSession().createQuery(hql);
        query.setString("username", username);
        return (User) query.uniqueResult();
    }

    public List<User> list(Integer start, Integer length, String searchInfo) {
        String hql = "from User u where u.username like:searchInfo";
        Query query = getSession().createQuery(hql);
        query.setString("searchInfo", "%" + searchInfo + "%");
        query.setFirstResult(start);
        query.setMaxResults(length);
        return query.list();
    }

}
