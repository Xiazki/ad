package com.ad.entity.project;

import com.ad.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by xiang on 2017/4/27.
 */
@Entity
@Table(name = "ad_project")
public class Project extends BaseEntity<Long> {

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_desc")
    private String desc;

    @Column(name = "ad_user_id")
    private Long userId;

    @Column(name = "type")
    private Integer type;

    @Column(name = "server_ip")
    private String serverIp;

    @Column(name = "path")
    private String path;

    @Column(name = "file")
    private String file;

    @Column(name = "status")
    private Integer status;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
