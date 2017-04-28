package com.ad.dao.project;

import com.ad.dao.base.BaseDao;
import com.ad.entity.project.Project;
import com.ad.entity.project.ProjectUser;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiang on 2017/4/27.
 */
@Repository
public class ProjectUserDao extends BaseDao<ProjectUser> {
    /**
     * 得到用户参与的所有项目
     *
     * @param userId
     * @return 项目列表
     */
    public List<Project> listUserProjectByUserId(Long userId) {
        String hql = "select p from Project p,ProjectUser pu where p.id = pu.projectId and pu.userId =:userId";
        Query query = getSession().createQuery(hql);
        query.setLong("userId", userId);
        return query.list();
    }

    /**
     * 得到给项目的参与人数
     *
     * @param projectId
     * @return Integer
     */
    public Integer countProjectUserByProjectId(Long projectId) {
        String hql = "from ProjectUser where projectId =:projectId";
        Query query = getSession().createQuery(hql);
        query.setLong("projectId", projectId);
        return query.list().size();
    }
}
