package com.ad.controller;

import com.ad.common.ResponseResult;
import com.ad.common.RestResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xiang on 2017/4/5.
 */
@Controller
public class BaseController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult unkonwExpection(Exception e){
        return RestResultGenerator.genErrorResult("服务器异常");
    }

}
