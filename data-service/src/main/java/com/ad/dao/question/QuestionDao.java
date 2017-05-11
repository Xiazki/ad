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

    public List<Question> listBuPrincipalId(Long userId, Long projectId, Integer type, Integer start, Integer length, String searchInfo) {
        String hql;
        if (type == 1) {
            hql = "from Question where principalId =:userId and projectId =:projectId and  ( status = 0 or status = 4) and title like:searchInfo";
        } else {
            hql = "from Question where creatorId =:userId and projectId =:projectId and  ( status = 0 or status = 4) and title like:searchInfo";
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
            hql = "from Question where principalId =:userId and projectId =:projectId and  ( status = 0 or status = 1) and title like:searchInfo";
        } else {
            hql = "from Question where creatorId =:userId and projectId =:projectId and  ( status = 0 or status = 1) and title like:searchInfo";
        }
        Query query = getSession().createQuery(hql);
        query.setLong("userId", userId);
        query.setString("searchInfo", "%" + searchInfo + "%");
        query.setLong("projectId", projectId);
        return query.list().size();
    }
}
