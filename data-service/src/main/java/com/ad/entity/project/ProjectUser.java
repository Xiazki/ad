package com.ad.entity.project;

import com.ad.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by xiang on 2017/4/27.
 */
@Entity
@Table(name = "ad_project_user")
public class ProjectUser extends BaseEntity<Long> {

    @Column(name = "ad_user_id")
    private Long userId;

    @Column(name = "project_id")
    private Long projectId;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
