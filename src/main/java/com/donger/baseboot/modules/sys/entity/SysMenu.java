package com.donger.baseboot.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.donger.baseboot.core.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author: szwei
 * @date : 2019/1/19 18:34
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysMenu extends BaseEntity<SysMenu> {

    public static final long LEVEL_ONE_MENU = 0L;
    @TableId
    private Long id;
    private Long parentId;
    private String name;
    private String label;

    private String orderNum;
    private String path;
    private String component;
    private String type;
    private String icon;
    private String isShow;
    private String permission;
    /**
     * 父菜单名称
     */
    @TableField(exist=false)
    private String parentName;

    /**
     * ztree属性
     */
    @TableField(exist=false)
    private Boolean open;

    @TableField(exist=false)
    private List<SysMenu> children;



}
