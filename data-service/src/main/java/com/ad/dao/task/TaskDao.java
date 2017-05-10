package com.ad.dao.task;

import com.ad.dao.base.BaseDao;
import com.ad.entity.task.Task;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiang on 2017/5/8.
 */
@Repository
public class TaskDao extends BaseDao<Task> {

    public List<Task> listByProjectId(Long projectId) {
        String hql = "from Task where projectId =:projectId order by createTime desc";
        Query query = getSession().createQuery(hql);
        query.setLong("projectId", projectId);
        return query.list();
    }
}
