package com.ad.ds.question;

import com.ad.dao.question.QuestionDao;
import com.ad.entity.question.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xiang on 2017/5/10.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class QuestionService {

    @Autowired
    private QuestionDao quetionDao;

    public void saveOrUpdate(Question question) {
        quetionDao.update(question);
    }

}
