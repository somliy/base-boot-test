package com.donger.baseboot.modules.sys.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.donger.baseboot.modules.sys.entity.SysRoleMenu;
import com.donger.baseboot.modules.sys.mapper.SysRoleMenuMapper;
import com.donger.baseboot.modules.sys.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: szwei
 * @date : 2019/1/20 13:35
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {
    @Override
    public List<Long> queryMenuIdList(Long roleId) {
        return null;
    }

    @Override
    public int deleteBatch(Long[] roleIds) {
        return 0;
    }
}
