package com.ad.vo.auth;

/**
 * Created by xiang on 2017/5/3.
 */
public class ChoosePermissionVo {

    private Long permissionId;

    private String permissionName;

    private String permissionDesc;

    private Boolean hasChoosed;

    private Boolean disable;

    public Boolean getHasChoosed() {
        return hasChoosed;
    }

    public void setHasChoosed(Boolean hasChoosed) {
        this.hasChoosed = hasChoosed;
    }

    public String getPermissionDesc() {
        return permissionDesc;
    }

    public void setPermissionDesc(String permissionDesc) {
        this.permissionDesc = permissionDesc;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }
}
