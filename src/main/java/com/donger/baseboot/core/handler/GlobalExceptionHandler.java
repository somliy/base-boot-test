package com.donger.baseboot.core.handler;

import com.donger.baseboot.core.common.exception.BizException;
import com.donger.baseboot.core.common.exception.CheckedException;
import com.donger.baseboot.core.utils.Res;
import com.donger.baseboot.core.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 全局异常处理器
 *
 * @author xyx
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {BizException.class, Exception.class})
    @ResponseStatus(HttpStatus.OK)
    public Result handleBizException(Exception e) {
        log.error("异常信息 ex={} , msg={}", e, e.getMessage());
        return Res.error("抱歉，服务器出错了！");
    }

    @ExceptionHandler(value = {CheckedException.class})
    @ResponseStatus(HttpStatus.OK)
    public Result handleCheckedException(Exception e) {
        log.error("异常信息 ex={} , msg={}", e, e.getMessage());
        return Res.error(e.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.OK)
    public Result handleBodyValidException(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        log.error("参数绑定异常,ex = {}", fieldErrors.get(0).getDefaultMessage());
        return Res.error(fieldErrors.get(0).getDefaultMessage());
    }


}
