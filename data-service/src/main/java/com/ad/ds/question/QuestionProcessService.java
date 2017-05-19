package com.ad.ds.question;

import com.ad.dao.question.QuestionProcessDao;
import com.ad.entity.question.QuestionProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xiang on 2017/5/11.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class QuestionProcessService {

    @Autowired
    private QuestionProcessDao questionProcessDao;

    public List<QuestionProcess> listByQuestionId(Long questionId) {
        return questionProcessDao.listByQuestionId(questionId);
    }

}
