package com.ad.controller;

import com.ad.ds.UserService;
import com.ad.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by xiang on 2017/4/6.
 */
@Controller
@RequestMapping("login")
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String test() {
        User user = userService.getUserByName("test");
        return "login";
    }
}
