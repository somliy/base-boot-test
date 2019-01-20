package com.donger.baseboot.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.donger.baseboot.modules.sys.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: szwei
 * @date : 2019/1/19 16:14
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

}
