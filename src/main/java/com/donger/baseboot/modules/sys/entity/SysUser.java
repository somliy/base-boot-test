package com.donger.baseboot.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.donger.baseboot.core.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author: szwei
 * @date : 2019/1/19 16:05
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUser extends BaseEntity<SysUser> {

    @TableId
    private Long id;
    private String username;
    private String password;
    private String nickName;
    private String email;
    private String phone;
    private Long deptId;

    private String userType;
    private String photo;
    private String status;

    private String remarks;

    @TableLogic
    private String delFlag;
    /**
     * 角色列表
     */
    @TableField(exist=false)
    private List<Long> roleIdList;

}
