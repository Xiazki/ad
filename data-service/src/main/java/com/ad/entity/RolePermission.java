package com.ad.entity;

import com.ad.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by xiang on 2017/4/19.
 */
@Table(name = "ad_role_permission")
@Entity
public class RolePermission extends BaseEntity<Long> {

    @Column(name = "ad_role_id")
    private Long roleId;

    @Column(name = "ad_permission_id")
    private Long permissionId;

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
