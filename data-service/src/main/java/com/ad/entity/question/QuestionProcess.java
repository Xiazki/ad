package com.ad.entity.question;

import com.ad.entity.base.BaseEntity;
import org.hibernate.annotations.CollectionId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by xiang on 2017/5/10.
 */
@Entity
@Table(name = "ad_question_process")
public class QuestionProcess extends BaseEntity<Long> {

    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "entity_id")
    private Long entityId;

    @Column(name = "entity_type")
    private Integer entityType;

    @Column(name = "event")
    private String event;

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

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
