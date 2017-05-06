package com.ad.biz;

import com.ad.common.constant.ChoosePermissionType;
import com.ad.ds.PermissionService;
import com.ad.ds.UserService;
import com.ad.entity.Permission;
import com.ad.entity.Role;
import com.ad.entity.User;
import com.ad.vo.auth.ChoosePermissionVo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * Created by xiang on 2017/5/3.
 */
@Component
public class ChoosePermissionBiz {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    public List<ChoosePermissionVo> listPermission(Long id, int type) {
        List<Permission> permissions;
        if (type == ChoosePermissionType.ROLE.getType()) {
            permissions = permissionService.listByRoleId(id);
        } else {
            permissions = permissionService.listByUserId(id);
        }
        List<ChoosePermissionVo> vos = Lists.newArrayList();
        List<Permission> all = permissionService.list();
        for (Permission p : all) {
            ChoosePermissionVo vo = new ChoosePermissionVo();
            vo.setPermissionId(p.getId());
            vo.setPermissionName(p.getPermissionName());
            vo.setPermissionDesc(p.getPermissionDesc());
            permissions.stream().filter(ps -> Objects.equals(p.getId(), ps.getId())).forEach(ps -> {
                vo.setHasChoosed(true);
            });
            vos.add(vo);
        }
        if (type == ChoosePermissionType.USER.getType()) {
            User user = userService.get(id);
            List<Permission> rp = Lists.newArrayList();
            for (Role role : user.getRoles()) {
                rp.addAll(permissionService.listByRoleId(role.getId()));
            }
            for (ChoosePermissionVo cpv : vos) {
                rp.stream().filter(p -> cpv.getPermissionId() == p.getId()).forEach(p -> {
                    cpv.setDisable(true);
                    cpv.setHasChoosed(true);
                });
            }
        }
        return vos;
    }
}
