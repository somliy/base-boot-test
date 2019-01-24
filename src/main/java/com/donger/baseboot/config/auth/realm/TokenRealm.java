package com.donger.baseboot.config.auth.realm;

import cn.hutool.core.util.StrUtil;
import com.donger.baseboot.config.auth.token.JwtToken;
import com.donger.baseboot.core.common.constant.CommonConstants;
import com.donger.baseboot.core.common.constant.SecurityConstants;
import com.donger.baseboot.core.common.entity.UserDetail;
import com.donger.baseboot.core.utils.JwtUtils;
import com.donger.baseboot.modules.sys.service.SysUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;


/**
 * 验证token  ，使用token 登陆
 */


@Slf4j
public class TokenRealm extends AuthorizingRealm {

    @Autowired
    private  SysUserService sysUserService;
    @Autowired
    private  RedisTemplate redisTemplate;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserDetail userDetail = (UserDetail) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(new HashSet<String>(userDetail.getPermissions()));
        return simpleAuthorizationInfo;

    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getPrincipal();

        String username = JwtUtils.getUsername(token);

        // redis  查询用户的token  如果过期则 报错
        String red_token = (String) redisTemplate.opsForValue().get(CommonConstants.DEFAULT_TOKEN_KEY + username);
        if (username == null || StrUtil.isEmpty(username) || red_token == null) {
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }

        // 否则更新过期时间
        redisTemplate.opsForValue().set(CommonConstants.DEFAULT_TOKEN_KEY + username, red_token, SecurityConstants.CODE_TIME, TimeUnit.MINUTES);

        UserDetail userDetail = new UserDetail();
        // 设置用户
        userDetail.setUser(sysUserService.queryByUserName(username));
        // 设置权限
        userDetail.setPermissions(sysUserService.queryAllPerms(userDetail.getUser().getId()));

        log.info("toekn认证 -> 用户为: {}", username);
        return new SimpleAuthenticationInfo(userDetail, token, super.getName());
    }
}
