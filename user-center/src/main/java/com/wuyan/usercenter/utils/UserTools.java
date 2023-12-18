package com.wuyan.usercenter.utils;

import com.wuyan.usercenter.model.domain.User;

import javax.servlet.http.HttpServletRequest;

import static com.wuyan.common.constants.UserConstants.ADMIN_ROLE;
import static com.wuyan.common.constants.UserConstants.USER_LOGIN_STATE;

/**
 * 用户管理工具类
 */
public class UserTools {
    /**
     * 用户权限校验
     * @param httpServletRequest
     * @return
     */
    public static boolean isAdmin(HttpServletRequest httpServletRequest){
       User user = (User)httpServletRequest.getSession().getAttribute(USER_LOGIN_STATE);
       if (user == null){
           return false;
       }
       if (user.getUserRole() != ADMIN_ROLE){
           return false;
       }
       return true;

    }
}
