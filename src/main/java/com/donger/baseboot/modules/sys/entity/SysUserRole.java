package com.donger.baseboot.modules.sys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: szwei
 * @date : 2019/1/19 18:49
 */
@Data
@AllArgsConstructor
public class SysUserRole {
    
    private Long userId;
    private Long roleId;
}
