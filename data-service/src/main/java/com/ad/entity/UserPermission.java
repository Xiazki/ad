package com.ad.entity;

import com.ad.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by xiang on 2017/4/19.
 */
@Table(name = "ad_user_permission")
@Entity
public class UserPermission extends BaseEntity<Long>{

    @Column(name = "ad_user_id")
    private Long userId;

    @Column(name = "ad_permission_id")
    private Long permissionId;

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
