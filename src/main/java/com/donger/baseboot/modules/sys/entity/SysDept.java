package com.donger.baseboot.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.donger.baseboot.core.entity.BaseEntity;
import lombok.Data;

/**
 * @Author: szwei
 * @date : 2019/1/19 18:37
 */
@Data
public class SysDept extends BaseEntity<SysDept> {

    @TableId
    private Long id;
    private Long parentId;
    private Long name;
    private Long orderNum;

}
