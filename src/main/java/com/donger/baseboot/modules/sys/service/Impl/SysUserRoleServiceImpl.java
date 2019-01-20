package com.donger.baseboot.modules.sys.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.donger.baseboot.modules.sys.entity.SysUser;
import com.donger.baseboot.modules.sys.entity.SysUserRole;
import com.donger.baseboot.modules.sys.mapper.SysUserRoleMapper;
import com.donger.baseboot.modules.sys.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: szwei
 * @date : 2019/1/20 9:46
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Override
    public List<Long> queryRoleIdList(Long userId) {
        return baseMapper.selectList(new QueryWrapper<SysUserRole>().eq("user_id",userId))
        .stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
    }

    @Override
    public int deleteBatch(Long[] roleIds) {
        return baseMapper.deleteBatchIds(Arrays.asList(roleIds));
    }
}
