package com.ad.common;

/**
 * Created by xiang on 2017/5/10.
 */
public enum QuestionStatus {
    OPEN(0, "打开"), CLOSE(1, "关闭"), SOLVE(3, "解决"), REOPEN(4, "重新打开");

    private Integer status;
    private String text;

    QuestionStatus(Integer status, String text) {
        this.status = status;
        this.text = text;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
