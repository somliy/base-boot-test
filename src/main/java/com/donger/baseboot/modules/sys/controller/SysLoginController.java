package com.donger.baseboot.modules.sys.controller;

import com.donger.baseboot.modules.sys.entity.SysUser;
import com.donger.baseboot.modules.sys.service.SysUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Author: szwei
 * @date : 2019/1/20 20:50
 */
@RestController
@AllArgsConstructor
@Slf4j
public class SysLoginController {

    private final SysUserService sysUserService;


    /**
     * 登录
     */
    @PostMapping("/sys/login")
    public String login(@RequestBody SysUser entity) throws IOException {
      /*  boolean captcha = sysCaptchaService.validate(form.getUuid(), form.getCaptcha());
        if (!captcha) {
            return R.error("验证码不正确");
        }*/

        /*//用户信息
        SysUser user = sysUserService.queryByUserName(entity.getUsername());

        //账号不存在、密码错误
        if (user == null || !user.getPassword().equals(entity.getPassword())) {
            return Res.error("账号或密码不正确");
        }

        //账号锁定
        if ("0".equals(user.getStatus()) && user.getStatus() != null) {
            return Res.error("账号已被锁定,请联系管理员");
        }

        return Res.ok("登录成功");*/

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(entity.getUsername(), entity.getPassword());
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            return e.getMessage();
        }
        return "login success";
    }


    /**
     * 退出
     */
    @PostMapping("/sys/logout")
    public String logout() {
        return "logout";
    }

}
