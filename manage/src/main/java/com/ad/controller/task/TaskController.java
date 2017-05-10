package com.ad.controller.task;

import com.ad.common.ContextHolder;
import com.ad.common.ResponseResult;
import com.ad.common.RestResultGenerator;
import com.ad.controller.BaseController;
import com.ad.ds.task.TaskService;
import com.ad.entity.project.Project;
import com.ad.entity.task.Task;
import com.ad.vo.task.TaskVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xiang on 2017/5/8.
 */
@Controller
@RequestMapping(value = "task")
public class TaskController extends BaseController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String toTaskListPage(Model model) {
        Project currentProject = ContextHolder.getCurrentProject();
        List<Task> list = taskService.listByProjectId(currentProject.getId());
        List<TaskVo> taskVos = list.stream().map(TaskVo::from).collect(Collectors.toList());
        model.addAttribute("taskVos", taskVos);
        return "task/list";
    }

    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult toTaskDetail(@PathVariable(value = "id") Long id) {
        Task task = taskService.getById(id);
        return RestResultGenerator.genResult(task, "请求成功");
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String toAddPage(HttpServletRequest request) {
        return "task/add_task";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult add(@RequestParam(value = "title") String title,
                              @RequestParam(value = "detail") String detail) {
        Project project = ContextHolder.getCurrentProject();
        taskService.saveTask(title, detail, getCurrentUserId(), project.getId());
        return RestResultGenerator.genResult("保存成功");
    }
}
