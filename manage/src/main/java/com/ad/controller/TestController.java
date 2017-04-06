package com.ad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by xiang on 2017/4/6.
 */
@Controller
@RequestMapping("login")
public class TestController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String test() {
        return "login";
    }
}
