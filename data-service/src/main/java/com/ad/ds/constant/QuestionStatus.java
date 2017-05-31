package com.ad.ds.constant;

/**
 * Created by xiang on 2017/5/10.
 */
public enum QuestionStatus {
    OPEN(0, "打开"), CLOSE(1, "关闭"),VERIFY(2,"验证"), SOLVE(3, "解决"), REOPEN(4, "重新打开"),REEDIT(5,"重新编辑"),TRANSFER(6,"转让");

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