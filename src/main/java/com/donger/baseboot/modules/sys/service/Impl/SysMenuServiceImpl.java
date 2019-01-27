package com.donger.baseboot.modules.sys.service.Impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.donger.baseboot.core.common.constant.CommonConstants;
import com.donger.baseboot.modules.sys.entity.SysMenu;
import com.donger.baseboot.modules.sys.mapper.SysMenuMapper;
import com.donger.baseboot.modules.sys.service.SysMenuService;
import com.donger.baseboot.modules.sys.service.SysRoleMenuService;
import com.donger.baseboot.modules.sys.service.SysUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: szwei
 * @date : 2019/1/19 19:37
 */
@Service
@AllArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    private final SysUserService sysUserService;
    private final SysRoleMenuService sysRoleMenuService;


    @Override
    public List<SysMenu> queryListParentId(Long parentId) {
        return this.list(new QueryWrapper<SysMenu>().eq("parent_id", parentId).orderByAsc("order_num"));
    }

    @Override
    public List<SysMenu> queryNotButtonList() {
        return this.list(new QueryWrapper<SysMenu>().ne("type", CommonConstants.MENU_TYPE_BUTTON).orderByAsc("order_num"));
    }

    @Override
    public List<SysMenu> getUserMenuList(Long userId) {
        //系统管理员，拥有最高权限
        if (CommonConstants.SUPER_ADMIN == userId) {
            // 查询全部菜单  用来展示
            return recursiveBuildTree(queryNotButtonList(), SysMenu.LEVEL_ONE_MENU);
        }

        return recursiveBuildTree(baseMapper.queryMenuByUserId(userId), SysMenu.LEVEL_ONE_MENU);
    }

    @Override
    public void delete(Long menuId) {
        //删除菜单
        baseMapper.deleteById(menuId);
        //删除菜单与角色关联
        sysRoleMenuService.deleteBatchByMenus(new Long[]{menuId});
    }


    /**
     * 递归建树
     *
     * @param sysMenuList 查询出的菜单数据
     * @param parentId    父节点ID
     * @return 递归后的树列表
     */
    private List<SysMenu> recursiveBuildTree(List<SysMenu> sysMenuList, Long parentId) {
        List<SysMenu> childList = sysMenuList.stream().filter(m -> m.getParentId().equals(parentId)).collect(Collectors.toList());
        if (CollectionUtil.isEmpty(childList)) {
            return null;
        }
        for (SysMenu sysMenu : childList) {
            sysMenu.setChildren(recursiveBuildTree(sysMenuList, sysMenu.getId()));
        }
        return childList;
    }
}
