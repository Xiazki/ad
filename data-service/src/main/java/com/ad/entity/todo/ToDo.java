package com.ad.entity.todo;

import com.ad.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by xiang on 2017/5/7.
 */
@Entity
@Table(name = "ad_todo")
public class ToDo extends BaseEntity<Long>{

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "ad_user_id")
    private Long userId;

    @Column(name = "status")
    private Integer status;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
