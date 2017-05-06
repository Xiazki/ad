package com.ad.entity;

import com.ad.entity.base.BaseEntity;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

/**
 * Created by xiang on 2017/4/7.
 */
@Entity
@Table(name = "ad_user")
public class User extends BaseEntity<Long> {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "department")
    private String department;

    @Column(name = "job")
    private String job;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    /**
     * 后面考虑不使用注解，使用中间表实体
     */

    @Deprecated
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ad_user_role", inverseJoinColumns = {@JoinColumn(name = "ad_role_id")}, joinColumns = {@JoinColumn(name = "ad_user_id")})
    private List<Role> roles;

    @Deprecated
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ad_user_permission", inverseJoinColumns = {@JoinColumn(name = "ad_permission_id")}, joinColumns = {@JoinColumn(name = "ad_user_id")})
    private List<Permission> permissions;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 将直接使用中间表代替
     * @return
     */
    @Deprecated
    public List<Role> getRoles() {
        return roles;
    }

    @Deprecated
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Deprecated
    public List<Permission> getPermissions() {
        return permissions;
    }

    @Deprecated
    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
