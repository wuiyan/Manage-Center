package com.wuyan.bookmanage;

import com.wuyan.bookmanage.empty.Student;
import org.junit.jupiter.api.Test;

public class SampleTest {
    @Test
    void NullPoint(){
        Student student =new Student();
        System.out.println(student.getSid());
    }
}
