package com.ad.biz;

import com.ad.ds.project.ProjectUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xiang on 2017/4/27.
 */
@Component
public class ProjectBiz {

    @Autowired
    private ProjectUserService projectUserService;


}
