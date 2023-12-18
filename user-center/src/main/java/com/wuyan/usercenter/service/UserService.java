package com.wuyan.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuyan.usercenter.model.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author wuyan
* @description 针对表【user】的数据库操作Service
* @createDate 2023-10-23 16:25:52
*/
public interface UserService extends IService<User> {
    /**
     * 用户注册
     * @param userAccount 用户账户名
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 ID
     */
    long userRegister(String userAccount,String userPassword,String checkPassword);

    /**
     *  用户登录
     * @param userAccount 用户账户名
     * @param userPassword  用户密码
     * @param httpServletRequest 用户请求会话
     * @return User实体类
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest httpServletRequest);

    /**
     *  用户登录状态检查
     * @param httpServletRequest 用来指明是那个用户发起的会话
     * @return User实体类
     */
    User userLoginCheck(HttpServletRequest httpServletRequest);

    /**
     * 用户信息修改
     */
    int userUpdate(User user);

    /**
     * 用户信息查询
     */
    List<User> userList(int pageNum, int size);

    /**
     * 用户信息总条数查询
     */
    long userCount();
    /**
     * 用户注销
     */
    int userLogout(HttpServletRequest httpServletRequest);

    /**
     * 获取脱敏之后的user对象
     * @param user
     * @return
     */
    User getSafeUser(User user);
}
