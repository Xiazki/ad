package com.ad.controller.todo;

import com.ad.common.ResponseResult;
import com.ad.common.RestResultGenerator;
import com.ad.controller.BaseController;
import com.ad.ds.todo.ToDoService;
import com.ad.entity.todo.ToDo;
import com.ad.vo.todo.ToDoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xiang on 2017/5/7.
 */
@Controller
@RequestMapping(value = "todo")
public class ToDoController extends BaseController {

    @Autowired
    private ToDoService toDoService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String toDoPage(Model model) {
        List<ToDo> toDos = toDoService.listByUserId(getCurrentUserId());
        model.addAttribute("vos", toDos.stream().map(ToDoVo::from).collect(Collectors.toList()));
        return "todo/list";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult add(@RequestParam(value = "title") String title,
                              @RequestParam(value = "content") String content) {
        toDoService.save(getCurrentUserId(), title, content);
        return RestResultGenerator.genResult("保存成功");
    }

    @RequestMapping(value = "complete", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult complete(@RequestParam(value = "id") Long id) {
        toDoService.updateStatus(id);
        return RestResultGenerator.genResult("修改成功");
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult delete(@RequestParam(value = "id") Long id) {
        toDoService.delete(id);
        return RestResultGenerator.genResult("修改成功");
    }
}
