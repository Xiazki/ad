package com.ad.ds.project;

import com.ad.dao.project.ProjectUserDao;
import com.ad.entity.project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xiang on 2017/4/27.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProjectUserService {

    @Autowired
    private ProjectUserDao projectUserDao;

    /**
     * 得到当前用户的所有参与项目
     *
     * @param userId
     * @return List<Project>
     */
    public List<Project> listProjectUserByUserId(Long userId) {
        return projectUserDao.listUserProjectByUserId(userId);
    }

    /**
     * 得到项目的参与人数
     *
     * @param projectId
     * @return Integer
     */
    public Integer countProjectUserByProjectId(Long projectId) {
        return projectUserDao.countProjectUserByProjectId(projectId);
    }
}
