package com.wuyan.bookmanage.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyan.bookmanage.empty.Student;
import com.wuyan.bookmanage.mapper.BookManageMapper;
import com.wuyan.bookmanage.mapper.StudentMapper;
import com.wuyan.bookmanage.service.StudentService;
import com.wuyan.common.common.ResponseCode;
import com.wuyan.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.wuyan.common.common.ResponseCode.PARAMETER_ERROR;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper,Student> implements StudentService {
    @Resource   // 使用注解将mapper注入到此处，此mapper是由Mybatis根据上述配置自动实现的。
    private BookManageMapper bookManageMapper;
    @Override
    public Student getStudentInfo(Integer num) {
        if (num<=0){
            throw new BusinessException(ResponseCode.PARAMETER_ERROR,"学生ID不能小于0");
        }
        return bookManageMapper.getStudentInfo(num);   // 返回根据mapper从数据库中映射出的对象
    }

    @Override
    public List<Student> getStudentAllInfo(int pageNum,int size) {
        // 1. 信息校验，判断页数和页面大小不小于等于零
        if (pageNum <= 0 || size <= 0){
            throw new BusinessException(PARAMETER_ERROR,"页数和页面大小不能小于零");
        }
        // 2. 获取学生列表
        Page<Student> page = new Page<>(pageNum,size);
        IPage<Student> studentIPage = this.page(page);
        return studentIPage.getRecords();
    }

    @Override
    public long getStudentCount() {
        Page<Student> page = new Page<>();
        IPage<Student> studentIPage = this.page(page);
        return studentIPage.getTotal();
    }

    @Override
    public int updateStudentInfo(Student student) {
        if (student.getSid() == null || student.getAge() == null){
            throw new BusinessException(ResponseCode.PARAMETER_ERROR);
        }
        if (student.getSid()<=0){
            throw new BusinessException(ResponseCode.PARAMETER_ERROR,"学生ID不能小于0");
        }
        if (student.getAge()<=0){
            throw new BusinessException(ResponseCode.PARAMETER_ERROR,"学生年龄不能小于0");
        }
        if (StringUtils.isAllBlank(student.getName(),student.getSex(),student.getSpeciality())){
            throw new BusinessException(ResponseCode.PARAMETER_ERROR,"参数不能为空");
        }

        return bookManageMapper.updateStudentInfo(student.getName(),student.getSex(),student.getAge(),student.getSpeciality(),student.getSid());
    }

    @Override
    public int insertStudentInfo(Student student) {
        if (student.getAge() == null){
            throw new BusinessException(ResponseCode.PARAMETER_ERROR);
        }

        if (student.getAge()<=0){
            throw new BusinessException(ResponseCode.PARAMETER_ERROR,"学生年龄不能小于0");
        }

        if (StringUtils.isAllBlank(student.getName(),student.getSex(),student.getSpeciality())){
            throw new BusinessException(ResponseCode.PARAMETER_ERROR,"参数不能为空");
        }
        return bookManageMapper.insertStudentInfo(student.getName(),student.getSex(),student.getAge(),student.getSpeciality());
    }

    @Override
    public int deleteStudentInfo(int sid) {
        if (sid<=0){
            throw new BusinessException(ResponseCode.PARAMETER_ERROR,"学生ID不能小于0");
        }
        return bookManageMapper.deleteStudentInfo(sid);
    }

}
