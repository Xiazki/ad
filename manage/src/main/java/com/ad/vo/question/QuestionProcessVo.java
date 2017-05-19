package com.ad.vo.question;

import com.ad.common.constant.Constants;
import com.ad.entity.question.QuestionProcess;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;

/**
 * Created by xiang on 2017/5/11.
 */
public class QuestionProcessVo {

    private Long id;

    private Long questionId;

    private Long entityId;

    private String userName;

    private Integer entityType;

    private String event;

    private String createTime;

    public static QuestionProcessVo from(QuestionProcess questionProcess) {
        if (questionProcess == null) {
            return null;
        }
        QuestionProcessVo questionProcessVo = new QuestionProcessVo();
        questionProcessVo.setId(questionProcess.getId());
        BeanUtils.copyProperties(questionProcess, questionProcessVo);
        questionProcessVo.setCreateTime(new DateTime(questionProcess.getCreateTime()).toString(Constants.DATE_FORMAT));
        return questionProcessVo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Integer getEntityType() {
        return entityType;
    }

    public void setEntityType(Integer entityType) {
        this.entityType = entityType;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
