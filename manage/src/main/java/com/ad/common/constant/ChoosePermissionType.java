package com.ad.common.constant;

/**
 * Created by xiang on 2017/5/3.
 */
public enum ChoosePermissionType {

    ROLE(1, "角色类型ID"), USER(2, "用户类型ID");

    ChoosePermissionType(int type, String text) {
        this.type = type;
        this.text = text;
    }

    private int type;
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
