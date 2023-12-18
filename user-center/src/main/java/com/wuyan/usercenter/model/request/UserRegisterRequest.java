package com.wuyan.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;
/**
 * 用户注册请求体
 * @author wuyan
 */
@Data
public class UserRegisterRequest implements Serializable {
    private static final long serialVersionUID = 3L;
    public String userAccount;
    public String userPassword;
    public String checkPassword;

}
