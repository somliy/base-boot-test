package com.donger.baseboot.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.donger.baseboot.modules.sys.entity.SysUser;

import java.util.List;

/**
 * @Author: szwei
 * @date : 2019/1/19 16:16
 */
public interface SysUserService extends IService<SysUser> {
    

    /**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 根据用户名，查询系统用户
     */
    SysUser queryByUserName(String username);

    /**
     * 修改密码
     * @param userId
     * @param password
     * @param newpassword
     * @return
     */
    boolean updatePassword(Long userId,String password,String newpassword);


    /**
     * 添加用户
     * @param entity
     * @return
     */
    boolean addUser(SysUser entity);

    /**
     * 修改用户
     * @param entity
     * @return
     */
    boolean updateUserById(SysUser entity);
}
