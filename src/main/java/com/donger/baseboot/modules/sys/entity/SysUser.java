package com.donger.baseboot.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.donger.baseboot.core.entity.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * @Author: szwei
 * @date : 2019/1/19 16:05
 */
@Data
public class SysUser extends BaseEntity<SysUser> {

    private Long id;
    private String username;
    private String password;
    private String nickName;
    private String email;
    private String phone;
    private Long deptId;

    private String userType;
    private String photo;
    private String loginFlag;

    private String remarks;
    /**
     * 角色列表
     */
    @TableField(exist=false)
    private List<Long> roleIdList;

}
