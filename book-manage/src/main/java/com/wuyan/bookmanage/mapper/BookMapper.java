package com.wuyan.bookmanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuyan.bookmanage.empty.Book;
import org.apache.ibatis.annotations.Select;

/**
* @author 86155
* @description 针对表【book】的数据库操作Mapper
* @createDate 2023-12-16 15:09:41
* @Entity generator.domain.Book
*/
public interface BookMapper extends BaseMapper<Book> {
    @Select("select count(*) from book")
    long bookCount();

}




