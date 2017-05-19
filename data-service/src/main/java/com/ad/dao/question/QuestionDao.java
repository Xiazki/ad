package com.ad.dao.question;

import com.ad.dao.base.BaseDao;
import com.ad.entity.question.Question;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiang on 2017/5/10.
 */
@Repository
public class QuestionDao extends BaseDao<Question> {

    /**
     * 简单测试数据方法 需要更改
     *
     * @param userId
     * @param projectId
     * @param type
     * @param start
     * @param length
     * @param searchInfo
     * @return
     */
    public List<Question> listBuPrincipalId(Long userId, Long projectId, Integer type, Integer start, Integer length, String searchInfo) {
        String hql;
        if (type == 1) {
            hql = "from Question where principalId =:userId and projectId =:projectId and  ( status = 0 or status = 2 or status = 4) and title like:searchInfo";
        } else {
            hql = "from Question where creatorId =:userId and projectId =:projectId and title like:searchInfo";
        }
        Query query = getSession().createQuery(hql);
        query.setLong("userId", userId);
        query.setString("searchInfo", "%" + searchInfo + "%");
        query.setLong("projectId", projectId);
        query.setMaxResults(length);
        query.setFirstResult(start);
        return query.list();
    }

    public Integer countByPrincipalId(Long userId, Long projectId, Integer type, Integer start, Integer length, String searchInfo) {
        String hql;
        if (type == 1) {
            hql = "from Question where principalId =:userId and projectId =:projectId and  ( status = 0 or status = 2 or status = 1) and title like:searchInfo";
        } else {
            hql = "from Question where creatorId =:userId and projectId =:projectId and title like:searchInfo";
        }
        Query query = getSession().createQuery(hql);
        query.setLong("userId", userId);
        query.setString("searchInfo", "%" + searchInfo + "%");
        query.setLong("projectId", projectId);
        return query.list().size();
    }

    public List<Question> listNeedVerify(Long userId, Long projectId, Integer start, Integer length, String searchInfo) {
        String hql = "from Question where creatorId=:userId and  projectId =:projectId and status = 2 and title like:searchInfo";
        Query query = getSession().createQuery(hql);
        query.setLong("userId", userId);
        query.setLong("projectId", projectId);
        query.setString("searchInfo", "%" + searchInfo + "%");
        query.setFirstResult(start);
        query.setMaxResults(length);
        return query.list();
    }

    public Integer countNeedVerify(Long userId, Long projectId, String searchInfo) {
        String hql = "from Question where creatorId=:userId and  projectId =:projectId and status = 2 and title like:searchInfo";
        Query query = getSession().createQuery(hql);
        query.setLong("userId", userId);
        query.setLong("projectId", projectId);
        query.setString("searchInfo", "%" + searchInfo + "%");
        return query.list().size();
    }

    public List<Question> listByType(Long projectId, Integer type, Integer start, Integer length, String searchInfo) {
        String hql = "from Question where projectId=:projectId and type=:type and title like:searchInfo";
        Query query = getSession().createQuery(hql);
        query.setLong("projectId", projectId);
        query.setInteger("type", type);
        query.setFirstResult(start);
        query.setMaxResults(length);
        query.setString("searchInfo", "%" + searchInfo + "%");
        return query.list();
    }

    public Integer countByType(Long projectId, Integer type, String searchInfo) {
        String hql = "from Question where projectId=:projectId and type=:type and title like:searchInfo";
        Query query = getSession().createQuery(hql);
        query.setLong("projectId", projectId);
        query.setInteger("type", type);
        query.setString("searchInfo", searchInfo);
        return query.list().size();
    }

}
