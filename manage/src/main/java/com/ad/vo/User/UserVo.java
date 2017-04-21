package com.ad.vo.User;

import com.ad.common.constant.Constants;
import com.ad.entity.User;
import com.ad.vo.base.BaseVo;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;

/**
 * Created by xiang on 2017/4/14.
 */
public class UserVo extends BaseVo<Long> {

    private String username;

    private String password;

    private String department;

    private String job;

    private String email;

    private String phone;

    @Override
    public String toString() {
        return "UserVo{" +
                "department='" + department + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", job='" + job + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", createTime='" + this.getCreateTime() + '\'' +
                ", createTime='" + this.getUpdateTime() + '\'' +
                '}';
    }

    public static UserVo from(User user) {
        if (user == null) {
            return null;
        }
        UserVo vo = new UserVo();
        BeanUtils.copyProperties(user, vo);
        DateTime createTime = new DateTime(user.getCreateTime());
        DateTime updateTime = new DateTime(user.getUpdateTime());
        vo.setCreateTime(createTime.toString(Constants.DATE_FORMAT));
        vo.setUpdateTime(updateTime.toString(Constants.DATE_FORMAT));
        return vo;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
