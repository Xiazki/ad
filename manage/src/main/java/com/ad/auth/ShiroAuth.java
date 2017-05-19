package com.ad.auth;

import com.ad.biz.ProjectUserBiz;
import com.ad.common.constant.Constants;
import com.ad.ds.UserService;
import com.ad.entity.Permission;
import com.ad.entity.Role;
import com.ad.entity.User;
import com.ad.vo.PrincipalVo;
import com.ad.vo.project.ProjectUserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by xiang on 2017/4/7.
 */
public class ShiroAuth extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectUserBiz projectUserBiz;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        PrincipalVo principalVo = (PrincipalVo) super.getAvailablePrincipal(principalCollection);
        User currentUser = userService.getUserByName(principalVo.getCurrentUserName());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //当前登陆用户角色
        Set<String> roles = currentUser.getRoles().stream().map(Role::getRoleName).collect(Collectors.toSet());
        simpleAuthorizationInfo.setRoles(roles);
        //当前登陆用户权限
        Set<String> permissions = currentUser.getPermissions().stream().map(Permission::getPermissionName).collect(Collectors.toSet());
        simpleAuthorizationInfo.setStringPermissions(permissions);

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = new String(token.getPassword());

        //加密
        PasswordService svc = new DefaultPasswordService();
        User user = userService.getUserByName(username);
        //密码匹配
        if (user != null && svc.passwordsMatch(password, user.getPassword())) {
            List<ProjectUserVo> vos = projectUserBiz.listProjectUserByUserId(user.getId());
            setSession(Constants.USERPROJECTSESSIONKEY, vos);
            return new SimpleAuthenticationInfo(PrincipalVo.from(user), token.getPassword(), getName());
        }
        return null;
    }

    private void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }
}
