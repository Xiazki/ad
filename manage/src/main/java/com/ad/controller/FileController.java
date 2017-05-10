package com.ad.controller;

import com.ad.biz.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by xiang on 2017/5/9.
 */
@RequestMapping(value = "/file")
@Controller
public class FileController extends BaseController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
        if (file != null && !file.getName().equals("")) {
            logger.info("============================");
            logger.info("文件名称:" + file.getName());
            logger.info("文件大小:" + file.getSize());
            logger.info("============================");
            String fileName = UUID.randomUUID().toString().replace("_", "").replace(".", "").substring(0, 5) + "." + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String path = request.getSession().getServletContext().getRealPath("/static/file");
            try {
                return FileHandler.uploadFile1(file.getInputStream(), fileName, path);
            } catch (IOException e) {
                e.printStackTrace();
                return "error|" + e.getMessage();
            }
        } else {
            return "error|file not found";
        }
    }
}
