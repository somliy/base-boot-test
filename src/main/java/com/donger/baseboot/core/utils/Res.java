package com.donger.baseboot.core.utils;


import com.donger.baseboot.core.common.constant.CommonConstants;

/**
 * 前端通用返回类
 *
 * @author xyx
 */
public class Res implements CommonConstants {

    private final static String SUCCESS = "success";

    private final static String FAILED = "failed";

    public static <T> Result<T> ok() {
        return new Result<T>(SUCCESS_CODE, SUCCESS);
    }

    public static <T> Result<T> ok(String message) {
        return new Result<T>(SUCCESS_CODE, message);
    }

    public static <T> Result<T> ok(T data) {
        return new Result<T>(SUCCESS_CODE, data, SUCCESS);
    }

    public static <T> Result<T> error() {
        return new Result<T>(FAIL_CODE, FAILED);
    }

    public static <T> Result<T> error(String message) {
        return new Result<T>(FAIL_CODE, message);
    }

    public static <T> Result<T> response(int code, String msg) {
        return new Result<T>(code, msg);
    }

    public static <T> Result<T> response(int code, String msg, T data) {
        return new Result<T>(code, data, msg);
    }


}
