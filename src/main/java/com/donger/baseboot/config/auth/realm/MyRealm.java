package com.donger.baseboot.config.auth.realm;

import cn.hutool.core.util.ArrayUtil;
import com.donger.baseboot.core.web.UserDetail;
import com.donger.baseboot.modules.sys.entity.SysUser;
import com.donger.baseboot.modules.sys.service.ShiroService;
import com.donger.baseboot.modules.sys.service.SysUserService;
import lombok.AllArgsConstructor;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 自定义 Relam
 */
@Component
@AllArgsConstructor
public class MyRealm extends AuthorizingRealm {

    private final SysUserService sysUserService;
    private final ShiroService shiroService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        SysUser user = (SysUser) principalCollection.getPrimaryPrincipal();
        Long userId = user.getId();
        Set<String> permissions = shiroService.getUserPermissions(userId);

        // 拥有权限
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {

        System.out.println("MyRealm doGetAuthenticationInfo...");


        UserDetail userDetail = new UserDetail();

        String username = (String) authenticationToken.getPrincipal();
        SysUser sysUser = sysUserService.queryByUserName(username);
        userDetail.setUser(sysUser);
        userDetail.setPermissions(new ArrayList<>(shiroService.getUserPermissions(sysUser.getId())));
        return new SimpleAuthenticationInfo(userDetail, sysUser.getPassword(), super.getName());
    }

}