package com.donger.baseboot.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.donger.baseboot.modules.sys.entity.SysUserRole;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: szwei
 * @date : 2019/1/20 9:46
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Long> queryRoleIdList(Long userId);

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);
}
