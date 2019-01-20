package com.donger.baseboot.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.donger.baseboot.core.entity.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * @Author: szwei
 * @date : 2019/1/19 17:00
 */
@Data
public class SysRole extends BaseEntity<SysRole> {

    private Long id;
    private String enname;
    private String name;
    private String orderNum;

    /**
     * 菜单列表
     */
    @TableField(exist=false)
    private List<Long> menuIdList;
}
