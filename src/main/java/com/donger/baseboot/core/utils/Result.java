package com.donger.baseboot.core.utils;


import com.donger.baseboot.core.common.constant.CommonConstants;
import lombok.Data;

import java.io.Serializable;

/**
 * 前端通用返回结果
 *
 * @author xyx
 */

@Data
public class Result<T> implements Serializable {

    private String msg = "success";

    private int code = CommonConstants.SUCCESS_CODE;

    private T data;

    public Result() {
        super();
    }

    public Result(int code) {
        this.code = code;
    }

    public Result(int code, T data) {
        this(code);
        this.data = data;
    }

    public Result(int code, String msg) {
        this(code);
        this.msg = msg;
    }

    public Result(int code, T data, String msg) {
        this(code, msg);
        this.data = data;
    }

    public Result<T> data(T data) {
        this.data = data;
        return this;
    }

    public Result<T> message(String msg) {
        this.msg = msg;
        return this;
    }
}
