package com.wuyan.common.exception;

import com.wuyan.common.common.ResponseCode;
import lombok.Data;

/**
 * 自定义业务异常
 */
@Data
public class BusinessException extends RuntimeException{
    private int code;
    private String description;

    public BusinessException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }
    public BusinessException(ResponseCode responseCode, String description) {
        super(responseCode.getMessage());
        this.code = responseCode.getCode();
        this.description = description;
    }
    public BusinessException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.code = responseCode.getCode();
        this.description = responseCode.getDescription();
    }
}
