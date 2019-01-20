package com.donger.baseboot.modules.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.donger.baseboot.core.utils.Res;
import com.donger.baseboot.core.utils.Result;
import com.donger.baseboot.modules.sys.entity.SysMenu;
import com.donger.baseboot.modules.sys.service.SysMenuService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @Author: szwei
 * @date : 2019/1/19 20:52
 */
@RestController
@RequestMapping("/sys/menu")
@AllArgsConstructor
@Slf4j
public class SysMenuController {
    
    private final SysMenuService sysMenuService;

    /**
     * 所有菜单列表
     */
    @GetMapping("/page")
    public Result<IPage<SysMenu>> page(Page<SysMenu> page, SysMenu entity){
        IPage<SysMenu> list = sysMenuService.page(page, Wrappers.query(entity));
        return Res.ok(list);
    }

    /**
     * 添加菜单
     * @param entity
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody SysMenu entity){
        sysMenuService.save(entity);
        return Res.ok();
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public Result delete(Long id){
        sysMenuService.removeById(id);
        return Res.ok();
    }

    /**
     * 批量删除菜单
     * @param ids
     * @return
     */
    @GetMapping("/beath/delete")
    public Result beathDelete(List ids){
        sysMenuService.removeByIds(ids);
        return Res.ok();
    }

    /**
     * 修改菜单
     */
    @PostMapping("/update")
    public Result update(@RequestBody SysMenu entity){
        sysMenuService.updateById(entity);
        return Res.ok();
    }
    /**
     * 根据id查询菜单信息
     */
    @PostMapping("/info/{menuId}")
    public Result userInfo(@PathParam("menuId") Long menuId){
        SysMenu menu = sysMenuService.getById(menuId);
        return Res.ok(menu);
    }

}
