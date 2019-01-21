package com.donger.baseboot.modules.sys.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.donger.baseboot.modules.sys.entity.SysRoleMenu;
import com.donger.baseboot.modules.sys.mapper.SysRoleMenuMapper;
import com.donger.baseboot.modules.sys.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: szwei
 * @date : 2019/1/20 13:35
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {
    @Override
    public List<Long> queryMenuIdList(Long roleId) {
        baseMapper.selectList(new QueryWrapper<SysRoleMenu>().eq("role_id",roleId))
                .stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
        return null;
    }

    @Override
    public boolean deleteBatchByRoles(Long[] roleIds) {
        baseMapper.delete(new QueryWrapper<SysRoleMenu>().in("role_id",roleIds));
        return true;
    }

    @Override
    public boolean deleteBatchByMenus(Long[] menuIds) {
        baseMapper.delete(new QueryWrapper<SysRoleMenu>().in("menu_id",menuIds));
        return true;
    }
}
