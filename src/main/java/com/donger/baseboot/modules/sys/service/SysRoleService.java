package com.donger.baseboot.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.donger.baseboot.modules.sys.entity.SysRole;

import java.util.List;

/**
 * @Author: szwei
 * @date : 2019/1/19 19:25
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 查询所有权限
     */
    List<SysRole> getAllRole();

    /**
     * 根据代码获取角色
     */
    SysRole getByEnName(String enName);

    /**
     * 批量删除
     * @param roleIds
     */
    void deleteBatch(Long[] roleIds);
}