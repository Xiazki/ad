package com.ad.controller;

import com.ad.biz.ChoosePermissionBiz;
import com.ad.common.PageBean;
import com.ad.common.ResponseResult;
import com.ad.common.RestResultGenerator;
import com.ad.common.constant.ChoosePermissionType;
import com.ad.ds.RolePermissionService;
import com.ad.ds.RoleService;
import com.ad.ds.exception.ParamException;
import com.ad.entity.Role;
import com.ad.vo.auth.ChoosePermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.terracotta.context.annotations.ContextAttribute;

import java.util.List;

/**
 * Created by xiang on 2017/4/21.
 */
@RequestMapping(value = "role")
@Controller
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private ChoosePermissionBiz choosePermissionBiz;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String toRoleListPage() {
        return "auth/role/list";
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public PageBean list() {
        PageBean<Role> pageBean = new PageBean<>();
        List<Role> roles = roleService.list();
        pageBean.setData(roles);
        pageBean.setRecordsFiltered(roles.size());
        pageBean.setRecordsTotal(roles.size());
        return pageBean;
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String toAddPage() {
        return "auth/role/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult add(Role role) {
        roleService.saveOrUpdate(role);
        return RestResultGenerator.genResult("添加成功");
    }

    @RequestMapping(value = "delete/{id}")
    @ResponseBody
    public ResponseResult delete(@PathVariable(value = "id") Long id) {
        roleService.delete(id);
        return RestResultGenerator.genResult("删除成功!");
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String toEditPage(@PathVariable(value = "id") Long id, Model model) {
        Role role = roleService.getById(id);
        model.addAttribute("role", role);
        model.addAttribute("isEdit", true);
        return "auth/role/add";
    }

    @RequestMapping(value = "toAddPermission/{id}")
    public String toAddPermission(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("id", id);
        return "auth/role/permission";
    }

    @RequestMapping(value = "choose/permission")
    @ResponseBody
    public PageBean<ChoosePermissionVo> listChoosePermission(@RequestParam(name = "id") Long roleId) {
        PageBean<ChoosePermissionVo> pageBean = new PageBean<>();
        List<ChoosePermissionVo> vos = choosePermissionBiz.listPermission(roleId, ChoosePermissionType.ROLE.getType());
        pageBean.setData(vos);
        pageBean.setRecordsTotal(vos.size());
        pageBean.setRecordsFiltered(vos.size());
        return pageBean;
    }

    @RequestMapping(value = "permission/add")
    @ResponseBody
    public ResponseResult addPermission(@RequestParam(name = "id") Long id,
                                        @RequestParam(name = "permissions[]") Long[] permissionIds) throws ParamException {
        rolePermissionService.save(id, permissionIds);
        return RestResultGenerator.genResult("保存成功!");
    }
}
