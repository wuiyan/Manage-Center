package com.wuyan.usercenter;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(scanBasePackages = {"com.wuyan.usercenter"})
@MapperScans({
        @MapperScan("com.wuyan.bookmanage.mapper"),
        @MapperScan("com.wuyan.usercenter.mapper")
})
public class UserCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCenterApplication.class, args);
    }

}
