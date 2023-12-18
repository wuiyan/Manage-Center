package com.wuyan.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wuyan.common.common.ResponseBody;
import com.wuyan.common.common.ResponseCode;
import com.wuyan.common.exception.BusinessException;
import com.wuyan.usercenter.model.domain.User;
import com.wuyan.usercenter.model.request.UserLoginRequest;
import com.wuyan.usercenter.model.request.UserRegisterRequest;
import com.wuyan.usercenter.service.UserService;
import com.wuyan.common.util.ResultUtils;
import com.wuyan.usercenter.utils.UserTools;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.wuyan.common.common.ResponseCode.PARAMETER_ERROR;

/**
 * 用户管理
 */
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 用户注册
     * @param userRegisterRequest 用户注册请求体
     * @return long型响应值
     */
    @PostMapping("/register")
    public ResponseBody<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
        if (userRegisterRequest == null){
            throw new BusinessException(ResponseCode.PARAMETER_NULL_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();

        long l = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtils.success(l);

    }

    /**
     * 用户登录
     * @param userLoginRequest 用户登录请求体
     * @param httpServletRequest 用户登录会话
     * @return 脱敏后的User对象
     */
    @PostMapping("/login")
    public ResponseBody<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest httpServletRequest){
        if (userLoginRequest == null){
            throw new BusinessException(ResponseCode.PARAMETER_NULL_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();

        User user = userService.userLogin(userAccount, userPassword, httpServletRequest);
        return ResultUtils.success(user);

    }

    /**
     * 用户登录状态检查
     * @param httpServletRequest 用户请求会话
     * @return User实体类
     */
    @PostMapping("/user-status")
    public ResponseBody<User> userLoginCheck(HttpServletRequest httpServletRequest){
        if (httpServletRequest == null){
            throw new BusinessException(ResponseCode.PARAMETER_NULL_ERROR);
        }

        User user = userService.userLoginCheck(httpServletRequest);
        return ResultUtils.success(user);
    }

    /**
     * 用户注销
     * @param httpServletRequest 用户请求会话
     * @return Integer 类型
     */
    @PostMapping("/logout")
    public ResponseBody<Integer> userLogout(HttpServletRequest httpServletRequest){
        if (httpServletRequest == null){
            throw new BusinessException(ResponseCode.PARAMETER_NULL_ERROR);
        }

        int i = userService.userLogout(httpServletRequest);
        return ResultUtils.success(i);
    }



    /**
     * 查询用户信息
     * @param username  用户名
     * @param httpServletRequest 请求会话
     * @return 用户列表信息
     */
    @GetMapping("/search")
    public ResponseBody<List<User>> searchUser(String username,HttpServletRequest httpServletRequest){
        if (!UserTools.isAdmin(httpServletRequest)){
            throw new BusinessException(ResponseCode.NO_AUTH);
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)){
            queryWrapper.like("username",username);
        }
        List<User> userList = userService.list(queryWrapper);
        List<User> users = userList.stream().map(user -> userService.getSafeUser(user)).collect(Collectors.toList());
        return ResultUtils.success(users);

    }
    /**
     * 获取用户列表信息
     * @param pageNum 当前页数
     * @param size 页面大小
     * @param httpServletRequest 请求会话
     * @return 用户列表信息
     */
    @GetMapping("/list")
    public ResponseBody<List<User>> userList(int pageNum,int size,HttpServletRequest httpServletRequest){
        if (!UserTools.isAdmin(httpServletRequest)){
            throw new BusinessException(ResponseCode.NO_AUTH);
        }

        if (pageNum <= 0 || size <= 0){
            throw new BusinessException(PARAMETER_ERROR,"页数和页面大小不能小于零");
        }


        List<User> userList = userService.userList(pageNum,size);
        List<User> users = userList.stream().map(user -> userService.getSafeUser(user)).collect(Collectors.toList());
        return ResultUtils.success(users);

    }

    /**
     * 获取用户信息条数
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/count")
    public ResponseBody<Long> userCount(HttpServletRequest httpServletRequest){
        if (!UserTools.isAdmin(httpServletRequest)){
            throw new BusinessException(ResponseCode.NO_AUTH);
        }

        long userCount = userService.userCount();
        return ResultUtils.success(userCount);

    }


    /**
     * 更新用户信息
     * User 接收用户更新信息
     * @param httpServletRequest 用户会话请求
     * @return Integer，是否成功
     */
    @PostMapping("/update")
    public ResponseBody<Integer> updateUser(@RequestBody User user,HttpServletRequest httpServletRequest){
        if (!UserTools.isAdmin(httpServletRequest)){
            throw new BusinessException(ResponseCode.NO_AUTH);
        }
        if (user == null){
            throw new BusinessException(ResponseCode.PARAMETER_NULL_ERROR);
        }

        int i = userService.userUpdate(user);
        return ResultUtils.success(i);
    }

    /**
     *  删除用户
     * @param map 接收ID信息
     * @param httpServletRequest 请求会话
     * @return 布尔值，是否成功
     */
    @PostMapping("/delete")
    public ResponseBody<Boolean> deleteUser(@RequestBody Map<String,String> map, HttpServletRequest httpServletRequest){
        if (!UserTools.isAdmin(httpServletRequest)){
            throw new BusinessException(ResponseCode.NO_AUTH);
        }
        long id = Long.parseLong(map.get("id"));
        if (id <= 0){
            throw new BusinessException(ResponseCode.PARAMETER_ERROR,"id不能小于零");

        }
        boolean removeById = userService.removeById(id);
        return ResultUtils.success(removeById);
    }



}
