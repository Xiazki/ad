package com.ad.controller;

import com.ad.bean.user.UserRequest;
import com.ad.biz.ChoosePermissionBiz;
import com.ad.common.PageBean;
import com.ad.common.ResponseResult;
import com.ad.common.RestResultGenerator;
import com.ad.common.constant.ChoosePermissionType;
import com.ad.ds.PermissionService;
import com.ad.ds.UserPermissionService;
import com.ad.ds.UserRoleService;
import com.ad.ds.UserService;
import com.ad.ds.exception.ParamException;
import com.ad.entity.Permission;
import com.ad.entity.Role;
import com.ad.entity.User;
import com.ad.vo.User.UserVo;
import com.ad.vo.auth.ChoosePermissionVo;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by xiang on 2017/4/14.
 */
@Controller
@RequestMapping(value = "user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserPermissionService userPermissionService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private ChoosePermissionBiz choosePermissionBiz;

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
        model.addAttribute("roleId", user.getRoles().get(0).getId());
        model.addAttribute("isEdit", true);
        return "auth/user/add";
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public ResponseResult delete(@PathVariable(value = "id") Long id) {
        userService.delete(id);
        return RestResultGenerator.genResult("删除成功");
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult add(UserRequest userRequest) throws ParamException {
        userService.saveOrUpdateUser(userRequest);
        return RestResultGenerator.genResult("保存成功！");
    }

    @RequestMapping(value = "toAddPermission/{id}")
    public String toAddPermission(@PathVariable(value = "id") Long userId, Model model) {
        model.addAttribute("id", userId);
        return "auth/user/permission";
    }

    @RequestMapping(value = "permission/list")
    @ResponseBody
    public PageBean listPermission(@RequestParam(name = "id") Long userId) {
        PageBean<ChoosePermissionVo> pageBean = new PageBean<>();
        List<ChoosePermissionVo> vos = choosePermissionBiz.listPermission(userId, ChoosePermissionType.USER.getType());
        pageBean.setData(vos);
        pageBean.setRecordsFiltered(vos.size());
        pageBean.setRecordsTotal(vos.size());
        return pageBean;
    }

    /**
     * 向用户添加权限
     *
     * @param userId
     * @param permissionIds
     * @return
     */
    @RequestMapping(value = "permission/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult addUserPermission(@RequestParam(value = "id") Long userId,
                                            @RequestParam(value = "permissions[]") List<Long> permissionIds) {
        userPermissionService.save(userId, permissionIds);
        return RestResultGenerator.genResult("保存成功");
    }

    @Deprecated
    @RequestMapping(value = "permission/add/{id}", method = RequestMethod.GET)
    public String toAddPermissionPage(@PathVariable(value = "id") Long userId, Model model) {
        List<Permission> up = permissionService.listByUserId(userId);
        Role role = userRoleService.getByUserId(userId);
        List<Permission> rp = permissionService.listByRoleId(role.getId());
        Set<Permission> ap = Sets.newHashSet();
        ap.addAll(up);
        ap.addAll(rp);
        model.addAttribute("permissions", ap);
        return "auth/user/permission";
    }
}
