package com.ad.dao.project;

import com.ad.dao.base.BaseDao;
import com.ad.entity.project.Project;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiang on 2017/4/27.
 */
@Repository
public class ProjectDao extends BaseDao<Project> {

    /**
     * 管理员下的所有项目
     *
     * @param userId
     * @return
     */
    public List<Project> listByUserId(Long userId) {
        String hql = "from Project where userId =:userId";
        Query query = getSession().createQuery(hql);
        query.setLong("userId", userId);
        return query.list();
    }
}
