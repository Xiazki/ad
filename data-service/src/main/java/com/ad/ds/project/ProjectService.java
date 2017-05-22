package com.ad.ds.project;

import com.ad.dao.project.ProjectDao;
import com.ad.entity.project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xiang on 2017/5/1.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProjectService {

    @Autowired
    private ProjectDao projectDao;

    public Project getById(Long id) {
        return projectDao.get(id);
    }

    public void saveOrUpdate(Project project) {
        projectDao.update(project);
    }

    public List<Project> listByUserId(Long userId, Integer start, Integer length, String searchInfo) {
        return projectDao.listByUserId(userId, start, length, searchInfo);
    }

    public int countByUserId(Long userId, String searchInfo) {
        return projectDao.countByUserId(userId, searchInfo);
    }

}
