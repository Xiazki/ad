package com.ad.entity;

import com.ad.entity.base.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by xiang on 2017/4/11.
 */
@Entity
@Table(name = "ad_permission")
public class Permission extends BaseEntity<Long> {

    @Column(name = "permission_name")
    private String permissionName;

    @Column(name = "permission_desc")
    private String permissionDesc;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Permission that = (Permission) o;

        if (permissionName != null ? !permissionName.equals(that.permissionName) : that.permissionName != null)
            return false;
        return permissionDesc != null ? permissionDesc.equals(that.permissionDesc) : that.permissionDesc == null;

    }

    @Override
    public int hashCode() {
        int result = permissionName != null ? permissionName.hashCode() : 0;
        result = 31 * result + (permissionDesc != null ? permissionDesc.hashCode() : 0);
        return result;
    }
}
