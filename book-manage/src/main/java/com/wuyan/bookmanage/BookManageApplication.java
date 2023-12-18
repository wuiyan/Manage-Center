package com.wuyan.bookmanage;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.wuyan.usercenter","com.wuyan.bookmanage","com.wuyan.common"})
//避免重复扫描mapper接口
// @MapperScans({
//        @MapperScan("com.wuyan.bookmanage.mapper"),
//        @MapperScan("com.wuyan.usercenter.mapper")
//})
public class BookManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookManageApplication.class,args);
    }
}
