package com.ad.controller;

import com.ad.biz.UserBiz;
import com.ad.vo.User.UserSearchInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by xiang on 2017/5/10.
 */
@Controller
@RequestMapping("search")
public class SearchController extends BaseController {

    @Autowired
    private UserBiz userBiz;

    /**
     * 搜索用户
     *
     * @param keyword
     * @param offset
     * @return
     */
    @RequestMapping("user")
    @ResponseBody
    public List<UserSearchInfo> searchUser(@RequestParam(value = "q") String keyword,
                                           @RequestParam(value = "page", defaultValue = "0") Integer offset) {
        return userBiz.searchUser(keyword, offset, 5);
    }

}
