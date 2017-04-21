package com.ad.controller;

import com.ad.bean.user.UserRequest;
import com.ad.common.PageBean;
import com.ad.common.ResponseResult;
import com.ad.common.RestResultGenerator;
import com.ad.ds.UserService;
import com.ad.ds.exception.ParamException;
import com.ad.entity.User;
import com.ad.vo.User.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xiang on 2017/4/14.
 */
@Controller
@RequestMapping(value = "user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String toUserPage() {
        return "auth/user/list";
    }

    @RequestMapping(value = "list")
    @ResponseBody
    public PageBean<UserVo> list(@RequestParam(name = "start") Integer start,
                                 @RequestParam(name = "length") Integer length,
                                 @RequestParam(name = "draw") Integer draw,
                                 @RequestParam(name = "search[value]") String searchInfo) {
        PageBean<UserVo> pageBean = new PageBean<>();
        List<User> users = userService.list(start, length, searchInfo);
        Integer count = userService.count();
        List<UserVo> userVos = users.stream().map(UserVo::from).collect(Collectors.toList());
        pageBean.setData(userVos);
        pageBean.setDraw(draw);
        pageBean.setRecordsTotal(count);
        pageBean.setRecordsFiltered(count);
        return pageBean;
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String toAddPage() {
        return "auth/user/add";
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String toEditPage(@PathVariable(value = "id") Long id, Model model) {
        User user = userService.get(id);
        model.addAttribute("id", id);
        model.addAttribute("user", user);
        model.addAttribute("isEdit", true);
        return "auth/user/add";
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public ResponseResult delete(@PathVariable(value = "id") Long id){
        userService.delete(id);
        return RestResultGenerator.genResult("删除成功");
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult add(UserRequest userRequest) throws ParamException {
        userService.saveOrUpdateUser(userRequest);
        return RestResultGenerator.genResult("保存成功！");
    }
}
