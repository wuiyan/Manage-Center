package com.wuyan.bookmanage.service;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wuyan.bookmanage.empty.Student;
import org.springframework.stereotype.Component;

import java.util.List;


public interface StudentService extends IService<Student> {
    Student getStudentInfo(Integer num);    // 获取单个Student信息
    List<Student> getStudentAllInfo(int pageNum,int size);       // 获取所有Student信息

    // 获取学生信息总数
    long getStudentCount();

    // 更新学生信息
    int updateStudentInfo(Student student);
    // 插入学生信息
    int insertStudentInfo(Student student);
    // 删除学生信息
    int deleteStudentInfo(int sid);
}
