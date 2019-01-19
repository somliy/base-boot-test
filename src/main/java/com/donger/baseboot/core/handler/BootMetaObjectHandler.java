package com.donger.baseboot.core.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.donger.baseboot.core.common.constant.CommonConstants;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 字段自动填充
 *
 * @author xyx
 * @date 2019-01-12 20:10
 */
@Component
public class BootMetaObjectHandler implements MetaObjectHandler, CommonConstants {

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime nowDate = LocalDateTime.now();
        this.setFieldValByName(CREATE_DATA, nowDate, metaObject);
        this.setFieldValByName(UPDATE_DATA, nowDate, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName(UPDATE_DATA, LocalDateTime.now(), metaObject);
    }
}

