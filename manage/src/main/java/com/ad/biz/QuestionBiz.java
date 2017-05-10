package com.ad.biz;

import com.ad.common.QuestionStatus;
import com.ad.ds.question.QuestionService;
import com.ad.entity.question.Question;
import com.ad.vo.question.QuestionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xiang on 2017/5/10.
 */
@Component
public class QuestionBiz {

    @Autowired
    private QuestionService questionService;

    public void saveQuestion(QuestionVo questionVo, Long creator, Long projectId) {
        Question question = new Question();
        question.setProjectId(projectId);
        question.setTitle(questionVo.getTitle());
        question.setStatus(QuestionStatus.OPEN.getStatus());
        question.setCreatorId(creator);
        question.setPrincipalId(questionVo.getPrincipalId());
        question.setStar(false);
        question.setPriority(questionVo.getPriority());
        question.setQuestionDesc(questionVo.getQuestionDesc());
        question.setCreateTime(System.currentTimeMillis());
        question.setUpdateTime(System.currentTimeMillis());
        questionService.saveOrUpdate(question);
    }
}
