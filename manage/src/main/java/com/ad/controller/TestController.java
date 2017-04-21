package com.ad.controller;

import com.ad.ds.UserService;
import com.ad.entity.Role;
import com.ad.entity.User;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by xiang on 2017/4/6.
 */
@Controller
@RequestMapping("test")
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String test() {
        User user = userService.get(8L);
        user.setUsername("test cascade");
        List<Role> roles = Lists.newArrayList();
        Role role = new Role();
        role.setId(1L);
        role.setRoleName("test2");
        role.setRoleDesc("test");
        Role role1 = new Role();
        role1.setId(2L);
        role1.setRoleName("test1");
        role1.setRoleDesc("test");
        roles.add(role);
        roles.add(role1);
        user.setRoles(roles);
        userService.save(user);
        return "login";
    }

}
