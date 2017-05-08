package com.ad.dao.todo;

import com.ad.dao.base.BaseDao;
import com.ad.entity.todo.ToDo;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiang on 2017/5/7.
 */
@Repository
public class ToDoDao extends BaseDao<ToDo> {

    public List<ToDo> listByUserId(Long userId) {
        String hql = "from ToDo where userId =:userId";
        Query query = getSession().createQuery(hql);
        query.setLong("userId", userId);
        return query.list();
    }
}
