package com.wuyan.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求体
 * @author wuyan
 */
@Data
public class UserLoginRequest implements Serializable {
    private static final long serialVersionUID = 2L;
    public String userAccount;
    public String userPassword;
}
