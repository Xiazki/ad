package com.ad.biz;

import com.ad.ds.UserService;
import com.ad.ds.project.ProjectService;
import com.ad.ds.project.ProjectUserService;
import com.ad.entity.User;
import com.ad.entity.project.Project;
import com.ad.vo.project.ProjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
