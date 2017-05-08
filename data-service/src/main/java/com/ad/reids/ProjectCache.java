package com.ad.reids;

import com.ad.entity.project.Project;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * Created by xiang on 2017/5/8.
 */
@Component
public class ProjectCache {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static String SELECTPROJECTKEYPREFIX = "select_project:";

    private final static String PROJECTSETKEYRPEFIX = "project_set:";


    @Autowired
    public RedisAdapter redisAdapter;

    public void setSelectProject(Long userId, Project project) {

        try {
            String value = JSON.toJSONString(project);
            redisAdapter.set(buildSelectProjectKey(userId), value);
        } catch (Exception e) {
            logger.error("set user select project :" + e.getMessage());
            e.printStackTrace();
        }
    }

    public Project getSelectProject(Long userId) {
        try {
            String proJSON = redisAdapter.get(buildSelectProjectKey(userId));
            return JSON.parseObject(proJSON, Project.class);
        } catch (Exception e) {
            logger.error("get user select project");
            e.printStackTrace();
        }
        return null;
    }


    private String buildSelectProjectKey(Long userId) {
        return SELECTPROJECTKEYPREFIX + userId;
    }
}
