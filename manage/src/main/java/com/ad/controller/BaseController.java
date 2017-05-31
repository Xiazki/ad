package com.ad.controller;

import com.ad.common.ContextHolder;
import com.ad.common.ResponseResult;
import com.ad.common.RestResultGenerator;
import com.ad.ds.exception.ParamException;
import com.ad.entity.User;
import com.ad.entity.project.Project;
import com.ad.vo.PrincipalVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xiang on 2017/4/5.
 */
@Controller
public class BaseController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ModelAttribute
    public void postModel(Model model) {

        RequestMapping re = this.getClass().getAnnotation(RequestMapping.class);
        if (re != null) {
            String url[] = re.value();
            model.addAttribute("active", url[0]);
        } else {
            model.addAttribute("active", "home");
        }
        Project project = ContextHolder.getCurrentProject();
        if (project != null) {
            model.addAttribute("selectProject", project);
        }

    }

    @ExceptionHandler(ParamException.class)
    @ResponseBody
    public ResponseResult paramException(ParamException e) {
        logger.error(e.getMessage());
        return RestResultGenerator.genErrorResult(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult unKnowException(Exception e, HttpServletRequest request) {
        e.printStackTrace();
        return RestResultGenerator.genErrorResult("服务器异常");
    }

    /**
     * 得到当前用户
     *
     * @return
     */
    protected PrincipalVo getPrincipalVo() {
        Subject subject = SecurityUtils.getSubject();
        return (PrincipalVo) subject.getPrincipal();
    }

    /**
     * 得到当前登陆用户ID
     *
     * @return
     */
    protected Long getCurrentUserId() {
        PrincipalVo principalVo = getPrincipalVo();
        return principalVo.getCurrentUserId();
    }
}
