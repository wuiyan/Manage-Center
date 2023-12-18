package com.wuyan.bookmanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuyan.bookmanage.empty.Borrow;
import org.apache.ibatis.annotations.Select;

/**
* @author 86155
* @description 针对表【borrow】的数据库操作Mapper
* @createDate 2023-12-16 15:09:29
* @Entity generator.domain.Borrow
*/
public interface BorrowMapper extends BaseMapper<Borrow> {
    @Select("select count(*) from borrow")
    long borrowCount();

}




