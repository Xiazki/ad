package com.ad.biz;

import com.ad.ds.UserService;
import com.ad.ds.constant.ProjectStatusEnum;
import com.ad.ds.project.ProjectService;
import com.ad.ds.project.ProjectUserService;
import com.ad.entity.User;
import com.ad.entity.project.Project;
import com.ad.vo.project.ProjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by xiang on 2017/4/27.
 */
@Component
public class ProjectBiz {

    @Autowired
    private ProjectUserService projectUserService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    public ProjectVo getById(Long id) {
        Project project = projectService.getById(id);
        ProjectVo vo = ProjectVo.from(project);
        User user = userService.get(project.getUserId());
        vo.setUserName(user.getUsername());
        return vo;
    }

    public void saveOrUpdate(ProjectVo projectVo) {
        // TODO: 2017/5/2 项目添加用户
        Project project = new Project();
        if (projectVo.getId() > 0) {
            project = projectService.getById(projectVo.getId());
            project.setUpdateTime(new Date().getTime());
            //TODO 判断当前项目状态，发送相关状态的事件并保存状态
        } else {
            project.setCreateTime(new Date().getTime());
        }
        project.setProjectName(projectVo.getProjectName());
        project.setType(projectVo.getType());
        project.setDesc(projectVo.getDesc());
        project.setStatus(ProjectStatusEnum.STOP.getStatus());// test
        projectService.saveOrUpdate(project);
    }
}
