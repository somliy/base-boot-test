package com.donger.baseboot.modules.sys.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.donger.baseboot.core.common.constant.CommonConstants;
import com.donger.baseboot.core.common.entity.UserDetail;
import com.donger.baseboot.core.common.exception.BizException;
import com.donger.baseboot.core.common.web.BaseController;
import com.donger.baseboot.core.utils.Res;
import com.donger.baseboot.core.utils.Result;
import com.donger.baseboot.modules.sys.entity.SysMenu;
import com.donger.baseboot.modules.sys.entity.SysUser;
import com.donger.baseboot.modules.sys.service.SysMenuService;
import com.sun.javafx.collections.MappingChange;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @Author: szwei
 * @date : 2019/1/19 20:52
 */
@RestController
@RequestMapping("/sys/menu")
@AllArgsConstructor
@Slf4j
public class SysMenuController extends BaseController {
    
    private final SysMenuService sysMenuService;
    /**
     * 所有菜单列表
     */
    @GetMapping("/list")
    public Result list(){
        List<SysMenu> menuAll = sysMenuService.getMenuAll();

        return Res.ok(menuAll);
    }

    /**
     * 导航菜单
     */
    @GetMapping("/nav")
    public Result nav(){
        UserDetail userDetail = getUserDetail();
        List<SysMenu> menuList = sysMenuService.getUserMenuList(userDetail.getUser().getId());
        List<String> strings = new ArrayList<>();
        strings.add("sys:schedule:info");
        strings.add("sys:menu:update");
        strings.add("sys:menu:delete");
        strings.add("sys:config:info");
        strings.add("sys:menu:list");
        strings.add("sys:config:save");
        strings.add("sys:config:update");
        strings.add("sys:schedule:resume");
        strings.add("sys:user:delete");
        strings.add("sys:config:list");
        strings.add("sys:user:update");
        strings.add("sys:role:list");
        strings.add("sys:menu:info");
        strings.add("sys:menu:select");
        strings.add("sys:schedule:update");
        strings.add("sys:schedule:save");
        strings.add("sys:role:select");
        strings.add("sys:user:list");
        strings.add("sys:menu:save");
        strings.add("sys:role:save");
        strings.add("sys:schedule:log");
        strings.add("sys:role:info");
        strings.add("sys:schedule:delete");
        strings.add("sys:role:update");
        strings.add("sys:schedule:list");
        strings.add("sys:user:info");
        strings.add("sys:schedule:run");
        strings.add("sys:config:delete");
        strings.add("sys:role:delete");
        strings.add("sys:user:save");
        strings.add("sys:schedule:pause");
        strings.add("sys:log:list");
        strings.add("sys:oss:all");

        Map<String, List> map = new HashMap<>();
        map.put("permissions", strings);
        map.put("data",menuList);
        return Res.ok(map);
    }
    /**
     * 选择菜单(添加、修改菜单)
     */
    @GetMapping("/select")
    public Result select(){
        //查询列表数据
        List<SysMenu> menuList = sysMenuService.queryNotButtonList();

        //添加顶级菜单
        SysMenu root = new SysMenu();
        root.setId(0L);
        root.setName("一级菜单");
        root.setParentId(-1L);
        root.setOpen(true);
        menuList.add(root);

        return Res.ok(menuList);
    }

    /**
     * 添加菜单
     * @param entity
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody SysMenu entity){
        //数据校验
        verifyForm(entity);
        entity.setCreateBy(this.getUserDetail().getUser().getId());
        entity.setCreateDate(LocalDateTime.now());
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
        //判断是否有子菜单或按钮
        List<SysMenu> menuList = sysMenuService.queryListParentId(id);
        if(menuList.size() > 0){
            return Res.error("请先删除子菜单或按钮");
        }
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
        //数据校验
        verifyForm(entity);
        entity.setUpdateBy(this.getUserDetail().getUser().getId());
        entity.setUpdateDate(LocalDateTime.now());
        sysMenuService.updateById(entity);
        return Res.ok();
    }
    /**
     * 根据id查询菜单信息
     */
    @PostMapping("/info/{menuId}")
    public Result userInfo(@PathVariable("menuId") Long menuId){
        SysMenu menu = sysMenuService.getById(menuId);
        return Res.ok(menu);
    }

    /**
     * 验证参数是否正确
     */
    private void verifyForm(SysMenu menu){
        if(StrUtil.hasEmpty(menu.getName())){
            throw new BizException("菜单名称不能为空");
        }

        if(menu.getParentId() == null){
            throw new BizException("上级菜单不能为空");
        }

        //菜单
        if(menu.getType() == CommonConstants.MENU_TYPE_MENU){
            if(StrUtil.hasEmpty(menu.getPath())){
                throw new BizException("菜单Path不能为空");
            }
        }

        //上级菜单类型
        String parentType = CommonConstants.MENU_TYPE_CATALOG;
        if(menu.getParentId() != 0){
            SysMenu parentMenu = sysMenuService.getById(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if(menu.getType() == CommonConstants.MENU_TYPE_CATALOG ||
                menu.getType() == CommonConstants.MENU_TYPE_MENU){
            if(parentType != CommonConstants.MENU_TYPE_CATALOG){
                throw new BizException("上级菜单只能为目录类型");
            }
            return ;
        }

        //按钮
        if(menu.getType() == CommonConstants.MENU_TYPE_BUTTON){
            if(parentType != CommonConstants.MENU_TYPE_BUTTON){
                throw new BizException("上级菜单只能为菜单类型");
            }
            return ;
        }
    }

}
