package com.ad.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by xiang on 2017/4/7.
 */
@Controller
public class HomeController extends BaseController {

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String home() {
        return "home/lists";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String defaultPage() {
        return "login";
    }

    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:login";
    }
}
