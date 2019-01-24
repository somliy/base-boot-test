package com.donger.baseboot.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.donger.baseboot.modules.sys.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: szwei
 * @date : 2019/1/19 19:31
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 根据用户ID 查询用户权限
     *
     * @param currentUserId 用户ID
     * @return 用户权限列表
     */
    List<SysMenu> queryMenuByUserId(@Param("userId") Long currentUserId);



}
