package com.ad.vo.task;

import com.ad.entity.task.Task;

/**
 * Created by xiang on 2017/5/8.
 */
public class TaskVo {

    private Long id;

    private String taskTitle;

    public static TaskVo from(Task task) {
        if (task == null) {
            return null;
        }
        TaskVo taskVo = new TaskVo();
        taskVo.setId(task.getId());
        taskVo.setTaskTitle(task.getTitle());
        return taskVo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }
}
