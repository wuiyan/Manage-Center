package com.wuyan.common.common;

import lombok.Data;

/**
 * 全局响应体对象
 */
@Data
public class ResponseBody<T> {
    /**
     * 响应码
     */
    private int code;
    /**
     * 响应数据
     */
    private T data;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 详细描述信息
     */
    private String description;

    public ResponseBody(ResponseCode responseCode,T data,String description){
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.description = description;
        this.data = data;
    }

    public ResponseBody(ResponseCode responseCode, T data) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.description = responseCode.getDescription();
        this.data= data;
    }
    public ResponseBody(ResponseCode responseCode, String description) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.description = description;
        this.data = null;
    }
    public ResponseBody(int code,String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
        this.data = null;
    }


}
