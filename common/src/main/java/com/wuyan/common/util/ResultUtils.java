package com.wuyan.common.util;

import com.wuyan.common.common.ResponseBody;
import com.wuyan.common.common.ResponseCode;

/**
 * 全局响应体工具类
 */
public class ResultUtils {
    /**
     * 成功
     * @param data  响应数据
     * @return      全局响应封装对象
     * @param <T>   响应数据类型
     */
    public static <T> ResponseBody<T> success(T data){
        return new ResponseBody<>(ResponseCode.SUCCESS,data);
    }

    /**
     * 失败
     * @param responseCode  响应码对象
     * @param description   错误详细描述
     * @return      全局响应封装对象
     * @param <T>   响应数据类型
     */
    public static <T> ResponseBody<T> error(ResponseCode responseCode,String description){
        return new ResponseBody<>(responseCode,description);
    }
    public static <T> ResponseBody<T> error(int code,String message ,String description){
        return new ResponseBody<>(code,message,description);
    }

}
