package com.ad.entity;

import com.ad.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by xiang on 2017/4/19.
 */
@Table(name = "ad_user_role")
@Entity
public class UserRole extends BaseEntity<Long> {

    @Column(name = "ad_user_id")
    private Long userId;

    @Column(name = "ad_role_id")
    private Long roleId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
