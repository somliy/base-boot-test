package com.donger.baseboot.core.common.exception;


/**
 * 全局业务异常
 *
 * @author xyx
 */
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BizException() {
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
