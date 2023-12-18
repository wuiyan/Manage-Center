package com.wuyan.bookmanage.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyan.bookmanage.empty.Borrow;
import com.wuyan.bookmanage.mapper.BookManageMapper;
import com.wuyan.bookmanage.mapper.BorrowMapper;
import com.wuyan.bookmanage.service.BorrowService;
import com.wuyan.common.common.ResponseCode;
import com.wuyan.common.exception.BusinessException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
@Component
public class BorrowServiceImpl extends ServiceImpl<BorrowMapper,Borrow> implements BorrowService {
    @Resource
    private BookManageMapper bookManageMapper;
    @Resource
    private BorrowMapper borrowMapper;
    @Override
    public Borrow getBorrowInfo(int id) {
        if (id<=0){
            throw new BusinessException(ResponseCode.PARAMETER_ERROR,"ID不能小于0");
        }
        return bookManageMapper.getBorrowInfo(id);
    }

    @Override
    public List<Borrow> getBorrowAllInfo(int pageNum,int size) {
        if (pageNum<=0||size<=0){
            throw new BusinessException(ResponseCode.PARAMETER_ERROR,"页面大小和请求页数不能小于0");
        }
        int startNum = (pageNum - 1) * size;
        return bookManageMapper.getBorrowAllInfo(startNum,size);
    }

    @Override
    public long getBorrowCount() {
        return borrowMapper.borrowCount();
    }

    @Override
    public int insertBorrowInfo(int sid, int bid, Date date) {
        if (sid<=0 || bid <=0){
            throw new BusinessException(ResponseCode.PARAMETER_ERROR);
        }
        if (date == null){
            throw new BusinessException(ResponseCode.PARAMETER_ERROR);
        }
        return bookManageMapper.insertBorrowInfo(sid,bid,date);
    }

    @Override
    public int deleteBorrowInfo(int id) {
        if (id<=0){
            throw new BusinessException(ResponseCode.PARAMETER_ERROR,"ID不能小于0");
        }
        return bookManageMapper.deleteBorrowInfo(id);
    }
}
