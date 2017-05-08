package com.ad.vo;

import com.ad.entity.User;
import com.ad.entity.project.Project;

import java.util.List;

/**
 * Created by xiang on 2017/4/11.
 */
public class PrincipalVo {

    private String currentUserName;

    private Long currentUserId;

    public static PrincipalVo from(User user) {
        if (user == null) {
            return null;
        }
        PrincipalVo principalVo = new PrincipalVo();
        principalVo.setCurrentUserId(user.getId());
        principalVo.setCurrentUserName(user.getUsername());
        return principalVo;
    }

    public Long getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(Long currentUserId) {
        this.currentUserId = currentUserId;
    }

    public String getCurrentUserName() {
        return currentUserName;
    }

    public void setCurrentUserName(String currentUserName) {
        this.currentUserName = currentUserName;
    }
}
