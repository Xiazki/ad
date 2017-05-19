package com.ad.dao.question;

import com.ad.dao.base.BaseDao;
import com.ad.entity.question.QuestionProcess;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiang on 2017/5/10.
 */
@Repository
public class QuestionProcessDao extends BaseDao<QuestionProcess> {

    public List<QuestionProcess> listByQuestionId(Long questionId) {
        String hql = "from QuestionProcess where questionId=:questionId order by createTime asc";
        Query query = getSession().createQuery(hql);
        query.setLong("questionId", questionId);
        return query.list();
    }
}
