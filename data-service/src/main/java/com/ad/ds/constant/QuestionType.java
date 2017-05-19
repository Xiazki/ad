package com.ad.ds.constant;

/**
 * Created by xiang on 2017/5/15.
 */
public enum QuestionType {
    PUBLIC(1,"公共的");

    QuestionType(Integer type, String text) {
        this.type = type;
        this.text = text;
    }

    private Integer type;
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
