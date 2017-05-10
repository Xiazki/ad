package com.ad.entity.question;

import com.ad.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by xiang on 2017/5/10.
 */
@Entity
@Table(name = "ad_question")
public class Question extends BaseEntity<Long> {

    @Column(name = "title")
    private String title;

    @Column(name = "creator_id")
    private Long creatorId;

    @Column(name = "ad_project_id")
    private Long projectId;

    @Column(name = "principal_id")
    private Long principalId;

    @Column(name = "question_desc")
    private String questionDesc;

    @Column(name = "status")
    private Integer status;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "type")
    private Integer type;

    @Column(name = "is_star")
    private boolean star;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
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

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getQuestionDesc() {
        return questionDesc;
    }

    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
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
}
