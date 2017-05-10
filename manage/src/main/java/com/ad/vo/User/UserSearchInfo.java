package com.ad.vo.User;

import com.ad.entity.User;

/**
 * Created by xiang on 2017/5/10.
 * select2
 */
public class UserSearchInfo {

    private String id;

    private String text;

    public static UserSearchInfo from(User user) {
        if (user == null) {
            return null;
        }
        UserSearchInfo userSearchInfo = new UserSearchInfo();
        userSearchInfo.setId(String.valueOf(user.getId()));
        userSearchInfo.setText(user.getUsername());
        return userSearchInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
