package com.ad.vo.todo;

import com.ad.common.constant.Constants;
import com.ad.entity.todo.ToDo;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;

/**
 * Created by xiang on 2017/5/7.
 */
public class ToDoVo {

    private Long id;

    private String title;

    private String content;

    private Integer status;

    private Long userId;

    private String createTime;

    private String updateTime;

    public static ToDoVo from(ToDo toDo) {
        if (toDo == null) {
            return null;
        }
        ToDoVo toDoVo = new ToDoVo();
        BeanUtils.copyProperties(toDo, toDoVo);
        toDoVo.setId(toDo.getId());
        toDoVo.setCreateTime(new DateTime(toDo.getUpdateTime()).toString(Constants.DATE_FORMAT));
        toDoVo.setUpdateTime(new DateTime(toDo.getUpdateTime()).toString(Constants.DATE_FORMAT));
        return toDoVo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
