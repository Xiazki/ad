package com.ad.ds.question;

import com.ad.dao.question.QuestionDao;
import com.ad.dao.question.QuestionProcessDao;
import com.ad.ds.constant.QuestionStatus;
import com.ad.entity.question.Question;
import com.ad.entity.question.QuestionProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * Created by xiang on 2017/5/10.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private QuestionProcessDao questionProcessDao;

    public void saveOrUpdate(Question question) {
        boolean isEdit = false;
        if (question.getId() != null) {
            isEdit = true;
        }
        questionDao.update(question);

        QuestionProcess questionProcess = new QuestionProcess();
        questionProcess.setCreateTime(System.currentTimeMillis());
        questionProcess.setQuestionId(question.getId());
        questionProcess.setEntityId(question.getCreatorId());
        if (isEdit) {
            questionProcess.setEntityType(QuestionStatus.REEDIT.getStatus());
        } else {
            questionProcess.setEntityType(QuestionStatus.OPEN.getStatus());
        }
        //添加问题处理时间线
        questionProcessDao.save(questionProcess);

    }

    public void transferToOther(Long id, Long userId) {
        Question question = getById(id);
        question.setPrincipalId(userId);
        question.setUpdateTime(System.currentTimeMillis());
        questionDao.update(question);

        QuestionProcess questionProcess = new QuestionProcess();
        questionProcess.setCreateTime(System.currentTimeMillis());
        questionProcess.setQuestionId(question.getId());
        questionProcess.setEntityId(question.getPrincipalId());
        questionProcess.setEntityType(QuestionStatus.TRANSFER.getStatus());
        questionProcessDao.save(questionProcess);
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

    public void updateQuestion(Long id, Long userId, Integer status, String event) {
        Question question = questionDao.get(id);
        if (Objects.equals(question.getCreatorId(), userId)) {
            question.setStatus(status);
        } else {
            //将问题置为验证状态
            question.setStatus(QuestionStatus.VERIFY.getStatus());

        }
        //更新问题状态
        questionDao.update(question);
        QuestionProcess questionProcess = new QuestionProcess();
        questionProcess.setCreateTime(System.currentTimeMillis());
        questionProcess.setQuestionId(id);
        questionProcess.setEntityId(userId);
        questionProcess.setEntityType(status);
        questionProcess.setEvent(event);
        //添加问题处理时间线
        questionProcessDao.save(questionProcess);
    }

    public List<Question> listNeedVerifyQuestion(Long userId, Long projectId, Integer start, Integer length, String searchInfo) {
        return questionDao.listNeedVerify(userId, projectId, start, length, searchInfo);
    }

    public Integer countNeedVerifyQuestion(Long userId, Long projectId, String searchInfo) {
        return questionDao.countNeedVerify(userId, projectId, searchInfo);
    }

    public List<Question> listByType(Long projectId, Integer type, Integer start, Integer length, String searchInfo) {
        return questionDao.listByType(projectId, type, start, length, searchInfo);
    }

    public Integer countByType(Long projectId, Integer type, String searchInfo) {
        return questionDao.countByType(projectId, type, searchInfo);
    }
}
