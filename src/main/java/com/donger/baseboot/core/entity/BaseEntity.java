package com.donger.baseboot.core.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @Author: szwei
 * @date : 2019/1/19 16:07
 */

@Getter
@Setter
public class BaseEntity<T extends Model> extends Model<T> {
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private Long createBy;
    private Long updateBy;
    private String remarks;
    private String delFlag;

}
