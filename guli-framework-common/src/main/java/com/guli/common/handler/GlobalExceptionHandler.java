package com.guli.common.handler;

import com.guli.common.exception.GuliException;
import com.guli.common.vo.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        e.printStackTrace();
        return R.error();
    }

    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public R guliError(GuliException e) {
        e.printStackTrace();
        return R.error().message(e.getMessage()).code(e.getCode());
    }

}
