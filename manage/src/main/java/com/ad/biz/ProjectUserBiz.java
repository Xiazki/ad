package com.ad.biz;

import com.ad.common.constant.Constants;
import com.ad.ds.UserService;
import com.ad.ds.project.ProjectUserService;
import com.ad.entity.project.Project;
import com.ad.vo.project.ProjectUserVo;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xiang on 2017/4/27.
 */
@Component
public class ProjectUserBiz {

    @Autowired
    private ProjectUserService projectUserService;

    @Autowired
    private UserService userService;

    public List<ProjectUserVo> listProjectUserByUserId(Long userId) {
        List<Project> projects = projectUserService.listProjectUserByUserId(userId);
        List<ProjectUserVo> projectUserVos = Lists.newArrayList();
        for (Project p : projects) {
            ProjectUserVo projectUserVo = new ProjectUserVo();
            projectUserVo.setCreateTime(new DateTime(p.getCreateTime()).toString(Constants.DATE_FORMAT));
            projectUserVo.setUpdateTime(new DateTime(p.getUpdateTime()).toString(Constants.DATE_FORMAT));
            projectUserVo.setId(p.getId());
            projectUserVo.setGroupNum(projectUserService.countProjectUserByProjectId(p.getId()));
            projectUserVo.setUserId(p.getUserId());
            projectUserVo.setUserName(userService.get(p.getUserId()).getUsername());
            projectUserVo.setProjectName(p.getProjectName());
            projectUserVo.setServiceId(p.getServerIp());
            projectUserVo.setStatus(p.getStatus());
            projectUserVo.setType(p.getType());
            projectUserVo.setDesc(p.getDesc());
            projectUserVos.add(projectUserVo);
        }

        return projectUserVos;
    }

}
