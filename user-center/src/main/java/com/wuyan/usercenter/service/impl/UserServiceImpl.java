package com.wuyan.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyan.common.exception.BusinessException;
import com.wuyan.usercenter.mapper.UserMapper;
import com.wuyan.usercenter.model.domain.User;
import com.wuyan.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.wuyan.common.common.ResponseCode.*;
import static com.wuyan.common.constants.UserConstants.USER_LOGIN_STATE;

/**
 * 用户服务实现类
* @author wuyan
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    public static final String SALT = "wuyan";

    @Resource
    private UserMapper usermapper;

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        //1.信息校验
        // 账户非空校验
        if (StringUtils.isAllBlank(userAccount,userPassword,checkPassword)){
            throw new BusinessException(PARAMETER_NULL_ERROR);
        }
        // 用户账户名长度校验
        if(userAccount.length() < 4){
            throw new BusinessException(PARAMETER_ERROR,"用户名过短");
        }
        // 用户密码长度校验
        if(userPassword.length() < 8 || checkPassword.length() < 8){
            throw new BusinessException(PARAMETER_ERROR,"用户密码过短");
        }
        // 两次密码是否相同校验
        if(!userPassword.equals(checkPassword)){
            throw new BusinessException(PARAMETER_ERROR,"两次密码不相同");
        }
        // 账户是否包含特殊字符
        String pattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？ ]";
        Matcher matcher = Pattern.compile(pattern).matcher(userAccount);
        if (matcher.find()){
            throw new BusinessException(PARAMETER_ERROR,"账户包含特殊字符");
        }
        // 判断账户是否重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        long count = this.count(queryWrapper);
        if (count > 0){
            throw new BusinessException(PARAMETER_ERROR,"账户已存在");
        }

        // 2. 密码加密
        String encryptionPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

        // 3. 插入用户数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptionPassword);
        boolean saveResult = this.save(user);
        if (!saveResult){
            throw new BusinessException(DATABASE_OPERATION_FAILED,"数据库保存失败");
        }
        return user.getId();
    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest httpServletRequest) {
        //1.信息校验
        // 账户非空校验
        if (StringUtils.isAllBlank(userAccount,userPassword)){
            throw new BusinessException(PARAMETER_NULL_ERROR);
        }
        // 用户账户名长度校验
        if(userAccount.length() < 4){
            throw new BusinessException(PARAMETER_ERROR,"用户名过短");
        }
        // 用户密码长度校验
        if(userPassword.length() < 8){
            throw new BusinessException(PARAMETER_ERROR,"用户密码过短");
        }
        // 账户是否包含特殊字符
        String pattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？ ]";
        Matcher matcher = Pattern.compile(pattern).matcher(userAccount);
        if (matcher.find()){
            throw new BusinessException(PARAMETER_ERROR,"账户包含特殊字符");
        }

        // 密码加密
        String encryptionPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

        // 2.登录校验，此处的账户是否被逻辑删除是通过框架本身的设置进行完成的，确保被逻辑删除的记录不会被查询到
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        queryWrapper.eq("userPassword",encryptionPassword);
        User user = this.getOne(queryWrapper);
        if (user == null){
            throw new BusinessException(LOGIN_ERROR);
        }
        // 3.用户信息脱敏
        User safeUser = getSafeUser(user);

        // 4.记录用户登录的session状态
        httpServletRequest.getSession().setAttribute(USER_LOGIN_STATE,safeUser);

        // 5.返回脱敏后的用户信息
        return safeUser;
    }

    @Override
    public User userLoginCheck(HttpServletRequest httpServletRequest) {
        // 获取当前用户存储在session中的信息
        User user = (User) httpServletRequest.getSession().getAttribute(USER_LOGIN_STATE);
        if (user == null){
            throw new BusinessException(NO_LOGIN);
        }
        return user;
    }

    @Override
    public int userUpdate(User user) {
        // 1. 信息校验
        if (user == null){
            throw new BusinessException(PARAMETER_NULL_ERROR);
        }

        if(StringUtils.isAllBlank(user.getUserAccount())){
            throw new BusinessException(PARAMETER_NULL_ERROR);
        }
        // 用户id校验
        if(user.getId()<=0){
            throw new BusinessException(PARAMETER_ERROR,"用户id不符合要求");
        }
        // 用户状态校验
        if(user.getUserStatus()<0||user.getUserStatus()>1){
            throw new BusinessException(PARAMETER_ERROR,"用户状态不符合要求");
        }
        // 用户角色校验
        if (user.getUserRole()<0||user.getUserRole()>1){
            throw new BusinessException(PARAMETER_ERROR,"用户角色不符合要求");
        }


        // 2. 更新用户数据
        int result = usermapper.updateUser(user);

        return result;
    }

    @Override
    public List<User> userList(int pageNum, int size) {
        // 1. 信息校验，判断页数和页面大小不小于等于零
        if (pageNum <= 0 || size <= 0){
            throw new BusinessException(PARAMETER_ERROR,"页数和页面大小不能小于零");
        }
        // 2. 获取用户列表
        Page<User> page = new Page<>(pageNum,size,true);
        IPage<User> userIPage = this.page(page);
        return userIPage.getRecords();
    }

    @Override
    public long userCount() {
        Page<User> page = new Page<>();
        IPage<User> userIPage = this.page(page);
        return userIPage.getTotal();
    }

    @Override
    public int userLogout(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().removeAttribute(USER_LOGIN_STATE);
        return 1;
    }


    @Override
    public User getSafeUser(User user){
        User safeUser = new User();
        safeUser.setId(user.getId());
        safeUser.setUsername(user.getUsername());
        safeUser.setUserAccount(user.getUserAccount());
        safeUser.setAvatarUrl(user.getAvatarUrl());
        safeUser.setGender(user.getGender());
        safeUser.setPhone(user.getPhone());
        safeUser.setEmail(user.getEmail());
        safeUser.setUserStatus(user.getUserStatus());
        safeUser.setUserRole(user.getUserRole());
        return safeUser;
    }


}




