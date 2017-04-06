package com.ad.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xiang on 2017/4/6.
 */
@Controller
@RequestMapping(value = "lo")
public class LoginController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

}
