package com.ad.ds.question;

import com.ad.dao.question.QuestionDao;
import com.ad.entity.question.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xiang on 2017/5/10.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    public void saveOrUpdate(Question question) {
        questionDao.update(question);
    }

    public List<Question> listByPrincipal(Long userId, Long projectId, Integer type, Integer start, Integer length, String searchInfo) {
        return questionDao.listBuPrincipalId(userId, projectId, type, start, length, searchInfo);
    }

    public Integer countByPrincipal(Long userId, Long projectId, Integer type, Integer start, Integer length, String searchInfo) {
        return questionDao.countByPrincipalId(userId, projectId, type, start, length, searchInfo);
    }

    public Question getById(Long id) {
        return questionDao.get(id);
    }
}
