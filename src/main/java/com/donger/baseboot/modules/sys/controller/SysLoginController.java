package com.donger.baseboot.modules.sys.controller;

import com.donger.baseboot.core.utils.Res;
import com.donger.baseboot.core.utils.Result;
import com.donger.baseboot.core.web.BaseController;
import com.donger.baseboot.core.web.UserDetail;
import com.donger.baseboot.modules.sys.entity.SysUser;
import com.donger.baseboot.modules.sys.form.SysLoginForm;
import com.donger.baseboot.modules.sys.service.SysUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: szwei
 * @date : 2019/1/20 20:50
 */
@RestController
@AllArgsConstructor
@Slf4j
public class SysLoginController  extends BaseController {

    private final SysUserService sysUserService;


    /**
     * 登录
     */
    @PostMapping("/sys/login")
    public Result login(@RequestBody SysLoginForm entity) {
        SysUser user = sysUserService.queryByUserName(entity.getUsername());
        if (user == null) {
            return Res.error("用户不存在");
        }
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(entity.getUsername(), entity.getPassword());
            subject.login(token);
        } catch (UnknownAccountException e) {
            return Res.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return Res.error("账号或密码不正确");
        } catch (LockedAccountException e) {
            return Res.error("账号已被锁定,请联系管理员");
        } catch (AuthenticationException e) {
            return Res.error("账户验证失败");
        }
        return Res.ok();
    }

    @GetMapping("info")
    public Result info(){
        UserDetail userDetail = getUserDetail();
        return Res.ok().data(userDetail);
    }


    /**
     * 退出
     */
    @PostMapping("/sys/logout")
    public String logout() {
        return "logout";
    }

}
