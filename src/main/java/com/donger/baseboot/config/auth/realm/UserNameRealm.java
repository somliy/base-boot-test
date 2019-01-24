package com.donger.baseboot.config.auth.realm;

import com.donger.baseboot.modules.sys.entity.SysUser;
import com.donger.baseboot.modules.sys.service.SysUserService;
import com.sun.tracing.dtrace.ArgsAttributes;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 用户名密码登陆
 */
@Slf4j
public class UserNameRealm extends AuthorizingRealm {

    @Autowired
    private  SysUserService sysUserService;



    /**
     * 判断登陆方式
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }


    /**
     * 密码登陆只做简单的登陆
     * 严格的登陆授权再token 模式下完成
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        log.info("用户登陆-> {}",token.getUsername());
        SysUser sysUser = sysUserService.queryByUserName(token.getUsername());
        return new SimpleAuthenticationInfo(sysUser.getUsername(), sysUser.getPassword(), getName());
    }

    /**
     * 该realm的名字
     *
     * @return name
     */
    @Override
    public String getName() {
        return "UserNameRealm";
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
