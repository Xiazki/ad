package com.ad.ds.project;

import com.ad.dao.project.ProjectDao;
import com.ad.entity.project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
