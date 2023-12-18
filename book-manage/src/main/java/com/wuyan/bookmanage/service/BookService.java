package com.wuyan.bookmanage.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.wuyan.bookmanage.empty.Book;

import java.util.List;


public interface BookService extends IService<Book> {
    Book getBookInfo(int bid);  // 获取单个书籍信息
    List<Book> getBookAllInfo(int pageNum,int size);    // 获取全部书籍信息

    // 获取书籍信息总数
    long getBookCount();

    // 更新书籍信息
    int updateBookInfo(String title, String desc, String coverImage, int bid);
    // 删除一条书籍信息
    int deleteBookInfo(int bid);
    // 插入一条书籍信息
    int insertBookInfo(String title,String desc,String coverImage);


}
