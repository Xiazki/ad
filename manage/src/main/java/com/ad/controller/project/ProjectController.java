package com.ad.controller.project;

import com.ad.biz.ProjectBiz;
import com.ad.biz.ProjectUserBiz;
import com.ad.common.ContextHolder;
import com.ad.common.PageBean;
import com.ad.common.ResponseResult;
import com.ad.common.RestResultGenerator;
import com.ad.controller.BaseController;
import com.ad.ds.UserService;
import com.ad.ds.project.ProjectService;
import com.ad.ds.project.ProjectUserService;
import com.ad.entity.User;
import com.ad.entity.project.Project;
import com.ad.reids.ProjectCache;
import com.ad.vo.project.ProjectUserVo;
import com.ad.vo.project.ProjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by xiang on 2017/4/27.
 */
@Controller
@RequestMapping(value = "project")
public class ProjectController extends BaseController {

    @Autowired
    private ProjectUserBiz projectUserBiz;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectBiz projectBiz;

    @Autowired
    private ProjectUserService projectUserService;

    @Autowired
    private ProjectCache projectCache;

    @RequestMapping(value = "/user/list")
    public String toUserProjectPage(Model model) {
        Long currentUserId = getCurrentUserId();
        List<ProjectUserVo> vos = projectUserBiz.listProjectUserByUserId(currentUserId);
        model.addAttribute("vos", vos);
        return "project/user_projects";
    }

    @RequestMapping(value = "/detail/{id}")
    public String toProjectDetail(@PathVariable(value = "id") Long id, Model model) {
        ProjectVo vo = projectBiz.getById(id);
        model.addAttribute("pv", vo);
        model.addAttribute("isEdit", true);
        return "project/project_detail";
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public ResponseResult save(ProjectVo projectVo) {
        projectBiz.saveOrUpdate(projectVo);
        return RestResultGenerator.genResult("保存成功!");
    }

    @RequestMapping(value = "/attend_user/list")
    @ResponseBody
    public PageBean userList(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "start") Integer start,
            @RequestParam(value = "length") Integer length,
            @RequestParam(value = "draw") Integer draw,
            @RequestParam(value = "searchInfo") String searchInfo) {
        PageBean<User> pageBean = new PageBean<>();
        int count = projectUserService.countProjectUserByProjectId(id);
        List<User> list = projectUserService.listUserByProjectId(id, start, length, searchInfo);
        pageBean.setData(list);
        pageBean.setDraw(draw);
        pageBean.setRecordsTotal(count);
        pageBean.setRecordsFiltered(count);
        return pageBean;
    }

    @RequestMapping(value = "select")
    @ResponseBody
    public ResponseResult userSelect(@RequestParam(value = "id") Long id) {
        Project project = projectService.getById(id);
        projectCache.setSelectProject(getCurrentUserId(), project);
        ContextHolder.setCurrentProject(project);
        return RestResultGenerator.genResult("success");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public PageBean<ProjectVo> list() {
        return null;
    }

    @RequestMapping(value = "/toProjectList", method = RequestMethod.GET)
    public String toProjectList() {
        return null;
    }

    @RequestMapping(value = "/selectProject")
    public String toSelectProjectPage() {
        return "SelectProject";
    }
}
