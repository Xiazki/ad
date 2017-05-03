package com.ad.ds.constant;

/**
 * Created by xiang on 2017/5/2.
 */
public enum ProjectStatusEnum {

    RUNNING(1, "运行中"), DEPLOYING(2, "部署中"), STOP(3, "已停止");

    ProjectStatusEnum(int status, String type) {
        this.status = status;
        this.type = type;
    }

    private int status;
    private String type;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
