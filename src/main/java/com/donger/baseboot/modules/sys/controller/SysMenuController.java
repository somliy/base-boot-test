package com.donger.baseboot.modules.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.donger.baseboot.core.common.constant.CommonConstants;
import com.donger.baseboot.core.common.exception.BizException;
import com.donger.baseboot.core.utils.Res;
import com.donger.baseboot.core.utils.Result;
import com.donger.baseboot.modules.sys.entity.SysMenu;
import com.donger.baseboot.modules.sys.service.SysMenuService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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
     * 所有菜单列表
     */
    @GetMapping("/list")
    public List<SysMenu> list(){
        List<SysMenu> menuList = sysMenuService.list();

        for(SysMenu sysMenu : menuList){
            SysMenu parentMenu = sysMenuService.getById(sysMenu.getParentId());
            if(parentMenu != null){
                sysMenu.setParentName(parentMenu.getName());
            }
        }
        return menuList;
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
        //数据校验
        verifyForm(entity);
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

    /**
     * 验证参数是否正确
     */
    private void verifyForm(SysMenu menu){
        if(StringUtils.isBlank(menu.getName())){
            throw new BizException("菜单名称不能为空");
        }

        if(menu.getParentId() == null){
            throw new BizException("上级菜单不能为空");
        }

        //菜单
        if(menu.getType() == CommonConstants.MENU_TYPE_MENU){
            if(StringUtils.isBlank(menu.getPath())){
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
