package com.donger.baseboot.config.auth.realm;

import com.donger.baseboot.modules.sys.entity.SysUser;
import com.donger.baseboot.modules.sys.service.ShiroService;
import com.donger.baseboot.modules.sys.service.SysUserRoleService;
import com.donger.baseboot.modules.sys.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.management.MemoryUsage;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义 Relam
 */
@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ShiroService shiroService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        SysUser user = (SysUser)principalCollection.getPrimaryPrincipal();
        Long userId = user.getId();
        Set<String> permissions = shiroService.getUserPermissions(userId);

        //拥有角色
/*        List<String> roles = sysUserRoleService.queryRoleIdList(userId);
        authorizationInfo.setRoles(roles);*/

        // 拥有权限
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {

        System.out.println("MyRealm doGetAuthenticationInfo...");

        String username = (String) authenticationToken.getPrincipal();
        SysUser sysUser = sysUserService.queryByUserName(username);
        if (sysUser == null) {
            throw new UnknownAccountException("当前账户不存在");
        }
        return new SimpleAuthenticationInfo(sysUser.getUsername(), sysUser.getPassword(), ByteSource.Util.bytes("TestSalt"), super.getName());
    }

}