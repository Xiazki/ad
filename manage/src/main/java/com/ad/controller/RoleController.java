package com.ad.controller;

import com.ad.common.PageBean;
import com.ad.common.ResponseResult;
import com.ad.entity.Role;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xiang on 2017/4/21.
 */
@RequestMapping(value = "role")
public class RoleController extends BaseController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String toRoleListPage() {

        return "auth/role/list";
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public PageBean list() {
        return null;
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String toAddPage() {
        return "auth/role/add";
    }

    public ResponseResult add(Role role) {
        return null;
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String toEditPage(@PathVariable(value = "id") Long id, Model model) {
        return "auth/role/add";
    }
}
