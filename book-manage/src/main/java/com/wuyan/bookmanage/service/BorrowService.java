package com.wuyan.bookmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuyan.bookmanage.empty.Borrow;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
@Component
public interface BorrowService extends IService<Borrow> {
    Borrow getBorrowInfo(int id);
    List<Borrow> getBorrowAllInfo(int pageNum,int size);

    // 获取借阅信息总数
    long getBorrowCount();

    // 插入一条借阅信息
    int insertBorrowInfo(@Param("sid")int sid, @Param("bid")int bid, @Param("date") Date date);

    int deleteBorrowInfo(int id);
}
