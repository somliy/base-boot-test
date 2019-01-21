package com.donger.baseboot.modules.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.donger.baseboot.core.utils.Res;
import com.donger.baseboot.core.utils.Result;
import com.donger.baseboot.modules.sys.entity.SysUser;
import com.donger.baseboot.modules.sys.form.PasswordForm;
import com.donger.baseboot.modules.sys.service.SysUserRoleService;
import com.donger.baseboot.modules.sys.service.SysUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: szwei
 * @date : 2019/1/19 16:20
 */

@RestController
@RequestMapping("/sys/user")
@AllArgsConstructor
@Slf4j
public class SysUserController {

    private final SysUserService sysUserService;
    private final SysUserRoleService sysUserRoleService;


    /**
     * 所有用户列表
     */
    @GetMapping("/page")
    public Result<IPage<SysUser>> page(Page<SysUser> page, SysUser entity){
        IPage<SysUser> list = sysUserService.page(page, Wrappers.query(entity));
        return Res.ok(list);
    }

    /**
     * 添加用户
     * @param entity
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody SysUser entity){
        sysUserService.saveOrUpdate(entity);
        return Res.ok();
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public Result delete(Long id){
        sysUserService.removeById(id);
        return Res.ok();
    }

    /**
     * 批量删除用户
     * @param ids
     * @return
     */
    @GetMapping("/beath/delete")
    public Result beathDelete(List ids){
        sysUserService.removeByIds(ids);
        return Res.ok();
    }

    /**
     * 修改用户
     */
    @PostMapping("/update")
    public Result update(@RequestBody SysUser entity){
        sysUserService.updateById(entity);
        return Res.ok();
    }

    /**
     * 根据id查询用户信息
     */
    @GetMapping("/info/{userId}")
    public Result userInfo(@PathVariable(value = "userId") Long userId){
        SysUser user = sysUserService.getById(userId+"");
        //获取用户所属的角色列表
        List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);
        return Res.ok(user);
    }

    /**
     * 修改登录用户密码
     */
    @PostMapping("/password")
    public Result password(@RequestBody PasswordForm form){
        //更新密码
        boolean flag = sysUserService.updatePassword(1L ,form.getPassword(),form.getNewPassword());
        if(!flag){
            return Res.error("原密码不正确");
        }
        return Res.ok();
    }
}
