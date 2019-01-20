package com.donger.baseboot.modules.sys.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.donger.baseboot.modules.sys.entity.SysRole;
import com.donger.baseboot.modules.sys.mapper.SysRoleMapper;
import com.donger.baseboot.modules.sys.service.SysRoleMenuService;
import com.donger.baseboot.modules.sys.service.SysRoleService;
import com.donger.baseboot.modules.sys.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: szwei
 * @date : 2019/1/19 19:26
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public List<SysRole> getAllRole() {
        return baseMapper.selectList(new QueryWrapper<SysRole>());
    }

    @Override
    public SysRole getByEnName(String enName) {
        return baseMapper.selectOne(new QueryWrapper<SysRole>().eq("en_name",enName));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除角色
        this.removeByIds(Arrays.asList(roleIds));

        //删除角色与菜单关联
        sysRoleMenuService.deleteBatch(roleIds);

        //删除角色与用户关联
        sysUserRoleService.deleteBatch(roleIds);
    }
}
