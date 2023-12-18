package com.wuyan.common.common;

/**
 * 响应状态码
 */
public enum ResponseCode {
    /**
     * 响应成功
     */
    SUCCESS(0, "操作成功", ""),
    /**
     * 前端请求参数错误
     */
    PARAMETER_ERROR(40000, "请求参数错误", ""),
    PARAMETER_NULL_ERROR(40001, "请求参数为空", ""),
    NO_LOGIN(40100, "用户未登录", ""),
    NO_AUTH(40101, "用户权限不足", ""),
    LOGIN_ERROR(40102, "登录失败", ""),
    /**
     * 数据库操作失败
     */
    DATABASE_OPERATION_FAILED(50000, "数据库操作失败", ""),

    SYSTEM_ERROR(50100, "系统错误", "");

    ResponseCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    /**
     * 响应码
     */
    private final int code;
    /**
     * 响应信息
     */
    private final String message;
    /**
     * 详细描述信息
     */
    private final String description;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
