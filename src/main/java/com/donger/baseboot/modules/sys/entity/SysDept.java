package com.donger.baseboot.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.donger.baseboot.core.common.entity.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * @Author: szwei
 * @date : 2019/1/19 18:37
 */
@Data
public class SysDept extends BaseEntity<SysDept> {

    @TableId
    private Long id;
    private Long parentId;
    private String name;
    private String orderNum;


    /**
     * ztree属性
     */
    @TableField(exist=false)
    private Boolean open;

    @TableField(exist=false)
    private List<?> list;

}
