package com.donger.baseboot.modules.sys.entity;

import com.donger.baseboot.core.entity.BaseEntity;
import lombok.Data;

/**
 * @Author: szwei
 * @date : 2019/1/19 18:37
 */
@Data
public class SysDept extends BaseEntity<SysDept> {

    private Long id;
    private Long parentId;
    private Long name;
    private Long orderNum;

}
