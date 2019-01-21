package com.donger.baseboot.modules.sys.service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.donger.baseboot.modules.sys.entity.SysUser;
import com.donger.baseboot.modules.sys.entity.SysUserRole;
import com.donger.baseboot.modules.sys.mapper.SysUserMapper;
import com.donger.baseboot.modules.sys.service.SysUserRoleService;
import com.donger.baseboot.modules.sys.service.SysUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @Author: szwei
 * @date : 2019/1/19 16:17
 */
@AllArgsConstructor
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final SysUserMapper sysUserMapper;
    private final SysUserRoleService sysUserRoleService;



    @Override
    public List<String> queryAllPerms(Long userId) {
        return sysUserMapper.queryAllPerms(userId);
    }

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return sysUserMapper.queryAllMenuId(userId);
    }

    @Override
    public SysUser queryByUserName(String username) {
        return baseMapper.selectOne(Wrappers.query(new SysUser()).eq("username",username));
    }

    @Override
    public boolean updatePassword(Long userId, String password, String newpassword) {
        SysUser sysUser = new SysUser();
        sysUser.setId(userId);
        sysUser.setPassword(password);
        return this.updateById(sysUser);
    }

    @Override
    @Transactional
    public boolean addUser(SysUser entity) {
        baseMapper.insert(entity);
        for(Long roleId:entity.getRoleIdList()){
            sysUserRoleService.save(new SysUserRole(entity.getId(),roleId));
        }
        return true;
    }

}
