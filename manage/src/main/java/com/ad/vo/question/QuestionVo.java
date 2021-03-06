package com.ad.vo.question;

import com.ad.common.constant.Constants;
import com.ad.entity.question.Question;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;

/**
 * Created by xiang on 2017/5/10.
 */
public class QuestionVo {

    private Long id;
    private String title;
    private String questionDesc;
    private Long principalId;
    private Integer priority;
    private Integer status;
    private Integer type;
    private boolean star;
    private String file;
    private String publicTitle;
    private String publicDesc;
    private Long creatorId;
    private String creatorName;
    private String principalName;
    private String createTime;

    public static QuestionVo from(Question question) {
        if (question == null) {
            return null;
        }
        QuestionVo questionVo = new QuestionVo();
        BeanUtils.copyProperties(question, questionVo);
        questionVo.setId(question.getId());
        questionVo.setCreatorName(new DateTime(question.getCreateTime()).toString(Constants.DATE_FORMAT));
        return questionVo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public boolean isStar() {
        return star;
    }

    public void setStar(boolean star) {
        this.star = star;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(Long principalId) {
        this.principalId = principalId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getQuestionDesc() {
        return questionDesc;
    }

    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getPublicDesc() {
        return publicDesc;
    }

    public void setPublicDesc(String publicDesc) {
        this.publicDesc = publicDesc;
    }

    public String getPublicTitle() {
        return publicTitle;
    }

    public void setPublicTitle(String publicTitle) {
        this.publicTitle = publicTitle;
    }
}
