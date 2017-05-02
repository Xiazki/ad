package com.ad.vo.project;

import com.ad.common.constant.Constants;
import com.ad.entity.project.Project;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;

/**
 * Created by xiang on 2017/5/1.
 */
public class ProjectVo {

    private Long id;

    private String projectName;

    private String desc;

    private String userName;

    private Long userId;

    private Integer type;

    private String serverIp;

    private String path;

    private String file;

    private Integer status;

    private String createTime;

    private String updateTime;

    public String getDesc() {
        return desc;
    }

    public static ProjectVo from(Project project) {
        if (project == null) {
            return null;
        }
        ProjectVo vo = new ProjectVo();
        BeanUtils.copyProperties(project,vo);
        vo.setCreateTime(new DateTime(project.getCreateTime()).toString(Constants.DATE_FORMAT));
        vo.setUpdateTime(new DateTime(project.getUpdateTime()).toString(Constants.DATE_FORMAT));
        return vo;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
