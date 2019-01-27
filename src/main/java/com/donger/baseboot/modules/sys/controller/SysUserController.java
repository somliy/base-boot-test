package com.donger.baseboot.modules.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.donger.baseboot.core.common.entity.UserDetail;
import com.donger.baseboot.core.common.web.BaseController;
import com.donger.baseboot.core.utils.Res;
import com.donger.baseboot.core.utils.Result;
import com.donger.baseboot.modules.sys.entity.SysUser;
import com.donger.baseboot.modules.sys.form.PasswordForm;
import com.donger.baseboot.modules.sys.service.SysUserService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: szwei
 * @date : 2019/1/19 16:20
 */

@RestController
@RequestMapping("/sys/user")
@AllArgsConstructor
@Slf4j
@Api(value = "用户管理模块")
public class SysUserController extends BaseController {

    private final SysUserService sysUserService;


    /**
     * 所有用户列表
     */
    @GetMapping("/page")
    public Result<IPage<SysUser>> page(Page<SysUser> page, SysUser entity) {
        IPage<SysUser> list = sysUserService.page(page, Wrappers.query(entity));
        return Res.ok(list);
    }

    /**
     * 添加用户
     *
     * @param entity
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody SysUser entity) {
        entity.setCreateBy(this.getUserDetail().getUser().getId());
        entity.setCreateDate(LocalDateTime.now());
        sysUserService.addUser(entity);
        return Res.ok();
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public Result delete(Long id) {
        sysUserService.removeById(id);
        return Res.ok();
    }

    /**
     * 批量删除用户
     *
     * @param ids
     * @return
     */
    @GetMapping("/beath/delete")
    public Result beathDelete(List ids) {
        sysUserService.removeByIds(ids);
        return Res.ok();
    }

    /**
     * 修改用户
     */
    @PostMapping("/update")
    public Result update(@RequestBody SysUser entity) {
        entity.setUpdateBy(this.getUserDetail().getUser().getId());
        entity.setUpdateDate(LocalDateTime.now());
        sysUserService.updateUserById(entity);
        return Res.ok();
    }

    /**
     * 根据id查询用户信息
     */
    @GetMapping("/info")
    public Result userInfo() {
        UserDetail userDetail = this.getUserDetail();
        return Res.ok(userDetail);
    }

    /**
     * 修改登录用户密码
     */
    @PostMapping("/password")
    public Result password(@RequestBody PasswordForm form) {
        //更新密码
        boolean flag = sysUserService.updatePassword(1L, form.getPassword(), form.getNewPassword());
        if (!flag) {
            return Res.error("原密码不正确");
        }
        return Res.ok();
    }
}
