package com.ad.ds.task;

import com.ad.dao.task.TaskDao;
import com.ad.entity.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xiang on 2017/5/8.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TaskService {

    @Autowired
    private TaskDao taskDao;

    public List<Task> listByProjectId(Long projectId) {
        return taskDao.listByProjectId(projectId);
    }

    public Task getById(Long id) {
        return taskDao.get(id);
    }

    //temp
    public void saveTask(String title,String detail,Long userId,Long projectId){
        Task task = new Task();
        task.setTitle(title);
        task.setDetail(detail);
        task.setUserId(userId);
        task.setProjectId(projectId);
        taskDao.save(task);
    }
}
