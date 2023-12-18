package com.wuyan.bookmanage.util;

import com.wuyan.common.exception.BusinessException;
import com.wuyan.usercenter.model.domain.User;

import javax.servlet.http.HttpServletRequest;

import static com.wuyan.common.common.ResponseCode.NO_LOGIN;
import static com.wuyan.common.constants.UserConstants.USER_LOGIN_STATE;

// 用户权限控制工具类
public class AuthCheck {

    // 用户是否登录
    public static boolean isLogin(HttpServletRequest httpServletRequest){
        User user = (User) httpServletRequest.getSession().getAttribute(USER_LOGIN_STATE);
        if (user == null){
            return false;
        }
        return true;
    }
}
