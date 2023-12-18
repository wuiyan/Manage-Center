package com.wuyan.usercenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuyan.usercenter.model.domain.User;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Qualifier;

/**
* @author 86155
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-10-23 16:25:52
* @Entity com.wuyan.usercenter.model.domain.User
*/
public interface UserMapper extends BaseMapper<User> {
    /**
     * 更新用户记录
     * @param user
     * @return
     */
    @Update("update user set username = #{username},userAccount = #{userAccount},avatarUrl=#{avatarUrl},gender=#{gender},phone=#{phone},email=#{email},userStatus=#{userStatus},userRole=#{userRole} where id = #{id}")
    int updateUser(User user);
}




