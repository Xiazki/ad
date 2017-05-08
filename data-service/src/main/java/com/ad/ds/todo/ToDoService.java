package com.ad.ds.todo;

import com.ad.dao.todo.ToDoDao;
import com.ad.entity.todo.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xiang on 2017/5/7.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ToDoService {

    @Autowired
    private ToDoDao toDoDao;

    public List<ToDo> listByUserId(Long userId) {
        return toDoDao.listByUserId(userId);
    }

    public void save(Long userId, String title, String content) {
        ToDo toDo = new ToDo();
        toDo.setCreateTime(System.currentTimeMillis());
        toDo.setStatus(1);
        toDo.setTitle(title);
        toDo.setContent(content);
        toDo.setUserId(userId);
        toDoDao.save(toDo);
    }

    public void updateStatus(Long id) {
        ToDo toDo = toDoDao.get(id);
        toDo.setStatus(2);
        toDoDao.update(toDo);
    }

    public void delete(Long id) {
        ToDo toDo = toDoDao.get(id);
        toDo.setStatus(3);
        toDoDao.update(toDo);
    }
}
