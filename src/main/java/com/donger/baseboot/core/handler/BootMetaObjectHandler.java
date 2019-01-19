package com.donger.baseboot.core.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.chillax.boot.core.common.constant.CommonConstants;
import com.chillax.boot.core.utils.SecurityUtil;
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
        SecurityUtil.getCurrentUserLogin().ifPresent(userDetail -> this.setFieldValByName(CREATE_BY, userDetail.getUserId(), metaObject));
        LocalDateTime nowDate = LocalDateTime.now();
        this.setFieldValByName(CREATE_TIME, nowDate, metaObject);
        this.setFieldValByName(UPDATE_TIME, nowDate, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName(UPDATE_TIME, LocalDateTime.now(), metaObject);
    }
}

