package com.wuyan.common.exception;

import com.wuyan.common.common.ResponseBody;
import com.wuyan.common.common.ResponseCode;
import com.wuyan.common.util.ResultUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 自定义异常处理类
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BusinessException.class})
    public ResponseBody businessExceptionHandler(BusinessException e){
        return ResultUtils.error(e.getCode(),e.getMessage(), e.getDescription());
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseBody businessExceptionHandler(RuntimeException e){
        return ResultUtils.error(ResponseCode.SYSTEM_ERROR,e.getMessage());
    }


}
