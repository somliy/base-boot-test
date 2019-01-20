package com.donger.baseboot.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

/**
 * @Author: szwei
 * @date : 2019/1/19 18:34
 */
@Data
public class SysMenu {

    private Long id;
    private Long parentId;
    private String name;
    private String orderNum;
    private String path;
    private String target;
    private String type;
    private String icon;
    private String isShow;
    private String permission;

    /**
     * ztree属性
     */
    @TableField(exist=false)
    private Boolean open;

    @TableField(exist=false)
    private List<?> list;



}
