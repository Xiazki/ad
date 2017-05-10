package com.ad.biz;

import com.ad.ds.UserService;
import com.ad.entity.User;
import com.ad.vo.User.UserSearchInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xiang on 2017/5/10.
 */
@Component
public class UserBiz {

    @Autowired
    private UserService userService;

    public List<UserSearchInfo> searchUser(String keyword, Integer offset, Integer length) {
        List<User> users = userService.list(offset, length, keyword);
        return users.stream().map(UserSearchInfo::from).collect(Collectors.toList());
    }
}
