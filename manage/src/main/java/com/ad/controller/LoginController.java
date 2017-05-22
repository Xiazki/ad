package com.ad.controller;

import com.ad.common.ResponseResult;
import com.ad.common.RestResultGenerator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Lists;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by xiang on 2017/4/6.
 */
@Controller
@RequestMapping(value = "login")
public class LoginController extends BaseController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult login(@RequestParam(name = "username") String username,
                                @RequestParam(name = "password") String password) {

        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(new UsernamePasswordToken(username, password));
            return RestResultGenerator.genResult("登陆成功");
        } catch (AuthenticationException var1) {
            var1.printStackTrace();
            return RestResultGenerator.genErrorResult("账号密码错误");
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public String toLoginPage() {
        return "login";
    }

    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:login";
    }
}
