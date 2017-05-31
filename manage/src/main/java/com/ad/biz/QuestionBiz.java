package com.ad.biz;

import com.ad.ds.UserService;
import com.ad.ds.constant.QuestionStatus;
import com.ad.ds.constant.QuestionType;
import com.ad.ds.question.QuestionProcessService;
import com.ad.ds.question.QuestionService;
import com.ad.entity.question.Question;
import com.ad.entity.question.QuestionProcess;
import com.ad.vo.question.QuestionProcessVo;
import com.ad.vo.question.QuestionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xiang on 2017/5/10.
 */
@Component
public class QuestionBiz {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionProcessService questionProcessService;

    @Autowired
    private UserService userService;

    public void saveQuestion(QuestionVo questionVo, Long creator, Long projectId) {
        Question question = new Question();
        if (questionVo.getId() > 0) {
            question = questionService.getById(questionVo.getId());
            question.setUpdateTime(System.currentTimeMillis());
        } else {
            question.setProjectId(projectId);
            question.setStatus(QuestionStatus.OPEN.getStatus());
            question.setCreatorId(creator);
            question.setUpdateTime(System.currentTimeMillis());
            question.setCreateTime(System.currentTimeMillis());
        }
        question.setStar(false);
        question.setTitle(questionVo.getTitle());
        question.setPrincipalId(questionVo.getPrincipalId());
        question.setPriority(questionVo.getPriority());
        question.setQuestionDesc(questionVo.getQuestionDesc());
        questionService.saveOrUpdate(question);
    }

    public List<QuestionVo> listNeedSolveQuestion(Long userId, Long projectId, Integer type, Integer start, Integer length, String searchInfo) {
        List<Question> list = questionService.listByPrincipal(userId, projectId, type, start, length, searchInfo);
        List<QuestionVo> vos = list.stream().map(QuestionVo::from).collect(Collectors.toList());
        for (QuestionVo v : vos) {
            v.setCreatorName(userService.get(v.getCreatorId()).getUsername());
            v.setPrincipalName(userService.get(v.getPrincipalId()).getUsername());
        }
        return vos;
    }

    public List<QuestionProcessVo> listQuestionProcess(Long questionId) {
        List<QuestionProcess> questionProcesses = questionProcessService.listByQuestionId(questionId);
        List<QuestionProcessVo> list = questionProcesses.stream().map(QuestionProcessVo::from).collect(Collectors.toList());
        for (QuestionProcessVo vo : list) {
            vo.setUserName(userService.get(vo.getEntityId()).getUsername());
        }
        return list;
    }

    public Integer countNeedSolveQuestion(Long userId, Long projectId, Integer type, Integer start, Integer length, String searchInfo) {
        return questionService.countByPrincipal(userId, projectId, type, start, length, searchInfo);
    }

    public QuestionVo getById(Long id) {
        Question question = questionService.getById(id);
        QuestionVo vo = QuestionVo.from(question);
        vo.setCreatorName(userService.get(question.getCreatorId()).getUsername());
        vo.setPrincipalName(userService.get(question.getPrincipalId()).getUsername());
        return vo;
    }

    public void transferToOther(Long id, Long userId) {

        questionService.transferToOther(id, userId);

    }

    public void updateQuestionStatus(Long id, Long userId, Integer status, String event) {
        questionService.updateQuestion(id, userId, status, event);
    }

    public List<QuestionVo> listNeedVerify(Long userId, Long projectId, Integer start, Integer length, String searchInfo) {
        List<Question> list = questionService.listNeedVerifyQuestion(userId, projectId, start, length, searchInfo);
        List<QuestionVo> vos = list.stream().map(QuestionVo::from).collect(Collectors.toList());
        for (QuestionVo v : vos) {
            v.setCreatorName(userService.get(v.getCreatorId()).getUsername());
            v.setPrincipalName(userService.get(v.getPrincipalId()).getUsername());
        }
        return vos;
    }

    public Integer countNeedVerify(Long userId, Long projectId, String searchInfo) {
        return questionService.countNeedVerifyQuestion(userId, projectId, searchInfo);
    }

    public void setQuestionToPublic(Long id, String title, String content) {
        Question question = questionService.getById(id);
        question.setType(QuestionType.PUBLIC.getType());
        question.setPublicTitle(title);
        question.setPublicDesc(content);
        questionService.saveOrUpdate(question);
    }
}
