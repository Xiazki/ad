package com.ad.vo.project;

/**
 * Created by xiang on 2017/4/27.
 */
public class ProjectUserVo {

    private Long id;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目服务器IP
     */
    private String serviceId;

    /**
     * 项目创建者ID
     */
    private Long userId;

    /**
     * 项目创建者用户名
     */
    private String userName;

    /**
     * 项目类型
     */
    private Integer type;

    /**
     * 项目状态
     */
    private Integer status;

    /**
     * 项目成员人数
     */
    private Integer GroupNum;

    private String createTime;

    private String updateTime;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getGroupNum() {
        return GroupNum;
    }

    public void setGroupNum(Integer groupNum) {
        GroupNum = groupNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
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
}
