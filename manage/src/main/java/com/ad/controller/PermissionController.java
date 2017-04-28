package com.ad.controller;

import com.ad.common.PageBean;
import com.ad.common.ResponseResult;
import com.ad.common.RestResultGenerator;
import com.ad.ds.PermissionService;
import com.ad.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by xiang on 2017/4/19.
 */
@Controller
@RequestMapping(value = "permission")
public class PermissionController extends BaseController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String toPermissionPage() {
        return "auth/permission/list";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String toAddPermissionPage() {
        return "auth/permission/add";
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String toEditPermissionPage(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("isEdit", true);
        Permission permission = permissionService.get(id);
        model.addAttribute("id", id);
        model.addAttribute("permission", permission);
        return "auth/permission/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult add(Permission permission) {
        permissionService.saveOrUpdate(permission);
        return RestResultGenerator.genResult("保存成功");
    }

    /**
     * 查询用户所有权限
     */
    @RequestMapping(value = "list/user", method = RequestMethod.GET)
    @ResponseBody
    public PageBean listByUserId(@RequestParam(value = "userId") Long userId) {

        return null;
    }

    /**
     * 查询角色下所有权限
     */
    @RequestMapping(value = "list/role", method = RequestMethod.GET)
    public PageBean listByRoleId(@RequestParam(value = "roleId") Long roleId) {

        return null;
    }

    /**
     * 查询所有权限
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public PageBean list() {
        List<Permission> permissions = permissionService.list();
        PageBean<Permission> pageBean = new PageBean<>();
        pageBean.setData(permissions);
        pageBean.setRecordsFiltered(permissions.size());
        pageBean.setRecordsTotal(permissions.size());
        return pageBean;
    }
}
