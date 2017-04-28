package com.ad.controller.project;

import com.ad.biz.ProjectBiz;
import com.ad.biz.ProjectUserBiz;
import com.ad.controller.BaseController;
import com.ad.ds.project.ProjectUserService;
import com.ad.vo.project.ProjectUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by xiang on 2017/4/27.
 */
@Controller
@RequestMapping(value = "project")
public class ProjectController extends BaseController {

    @Autowired
    private ProjectUserService projectUserService;

    @Autowired
    private ProjectBiz projectBiz;

    @Autowired
    private ProjectUserBiz projectUserBiz;

    @RequestMapping(value = "/user/list")
    public String toUserProjectPage(Model model) {
        Long currentUserId = getCurrentUserId();
        List<ProjectUserVo> vos = projectUserBiz.listProjectUserByUserId(currentUserId);
        model.addAttribute("vos", vos);
        return "project/user_projects";
    }
}
