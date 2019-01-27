package com.donger.baseboot.modules.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.donger.baseboot.core.common.web.BaseController;
import com.donger.baseboot.core.utils.Res;
import com.donger.baseboot.core.utils.Result;
import com.donger.baseboot.modules.sys.entity.SysRole;
import com.donger.baseboot.modules.sys.service.SysRoleMenuService;
import com.donger.baseboot.modules.sys.service.SysRoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: szwei
 * @date : 2019/1/19 20:45
 */
@RestController
@RequestMapping("/sys/role")
@AllArgsConstructor
@Slf4j
public class SysRoleController extends BaseController {

    private final SysRoleService sysRoleService;
    private final SysRoleMenuService sysRoleMenuService;


    /**
     * 所有角色列表
     */
    @GetMapping("/page")
    public Result<IPage<SysRole>> page(Page<SysRole> page, SysRole entity) {
        IPage<SysRole> list = sysRoleService.page(page, Wrappers.query(entity));
        return Res.ok(list);
    }

    /**
     * 添加角色
     *
     * @param entity
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody SysRole entity) {
        entity.setCreateBy(this.getUserDetail().getUser().getId());
        entity.setCreateDate(LocalDateTime.now());
        sysRoleService.addRole(entity);
        return Res.ok();
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public Result delete(Long id) {
        sysRoleService.removeById(id);
        return Res.ok();
    }

    /**
     * 批量删除角色
     *
     * @param ids
     * @return
     */
    @GetMapping("/beath/delete")
    public Result beathDelete(List ids) {
        sysRoleService.removeByIds(ids);
        return Res.ok();
    }

    /**
     * 修改角色
     */
    @PostMapping("/update")
    public Result update(@RequestBody SysRole entity) {
        entity.setUpdateBy(this.getUserDetail().getUser().getId());
        entity.setUpdateDate(LocalDateTime.now());
        sysRoleService.updateRoleById(entity);
        return Res.ok();
    }

    /**
     * 根据id查询角色信息
     */
    @PostMapping("/info/{roleId}")
    public Result userInfo(@PathParam("roleId") Long roleId) {
        SysRole role = sysRoleService.getById(roleId);
        //查询角色对应的菜单
        List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
        role.setMenuIdList(menuIdList);
        return Res.ok(role);
    }
}
