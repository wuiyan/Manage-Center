package com.wuyan.bookmanage.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyan.bookmanage.empty.Book;
import com.wuyan.bookmanage.mapper.BookManageMapper;
import com.wuyan.bookmanage.mapper.BookMapper;
import com.wuyan.bookmanage.service.BookService;
import com.wuyan.common.common.ResponseCode;
import com.wuyan.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class BookServiceImpl extends ServiceImpl<BookMapper,Book> implements BookService {
    @Resource
    private BookManageMapper bookManageMapper;
    @Resource
    private BookMapper bookMapper;

    @Override
    public Book getBookInfo(int bid) {
        if (bid<=0){
            throw new BusinessException(ResponseCode.PARAMETER_ERROR,"BID不能小于0");
        }
        return bookManageMapper.getBookInfo(bid);
    }

    @Override
    public List<Book> getBookAllInfo(int pageNum, int size) {
        if (pageNum<=0||size<=0){
            throw new BusinessException(ResponseCode.PARAMETER_ERROR,"页面大小和请求页数不能小于0");
        }
        int startNum = (pageNum - 1) * size;
        return bookManageMapper.getBookAllInfo(startNum,size);


    }

    @Override
    public long getBookCount() {
        long bookCount = bookMapper.bookCount();
        return bookCount;
    }


    @Override
    public int updateBookInfo(String title, String desc, String coverImage, int bid) {
        if (bid<=0){
            throw new BusinessException(ResponseCode.PARAMETER_ERROR,"BID不能小于0");
        }
        if (StringUtils.isAllBlank(title,desc)){
            throw new BusinessException(ResponseCode.PARAMETER_ERROR);
        }
        return bookManageMapper.updateBookInfo(title, desc,coverImage,bid);
    }

    @Override
    public int deleteBookInfo(int bid) {
        if (bid<=0){
            throw new BusinessException(ResponseCode.PARAMETER_ERROR,"BID不能小于0");
        }
        return bookManageMapper.deleteBookInfo(bid);
    }

    @Override
    public int insertBookInfo(String title, String desc, String coverImage) {
        if (StringUtils.isAllBlank(title,desc)){
            throw new BusinessException(ResponseCode.PARAMETER_ERROR);
        }
        return bookManageMapper.insertBookInfo(title, desc, coverImage);
    }
}
