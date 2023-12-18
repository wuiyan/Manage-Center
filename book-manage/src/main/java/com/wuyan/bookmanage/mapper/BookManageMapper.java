package com.wuyan.bookmanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuyan.bookmanage.empty.Book;
import com.wuyan.bookmanage.empty.Borrow;
import com.wuyan.bookmanage.empty.Student;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface BookManageMapper {
    @Select("select * from student where sid = #{sid}")
    @Results({
            @Result(property = "sid",column = "sid"),
            @Result(property = "name",column = "name"),
            @Result(property = "sex",column = "sex"),
            @Result(property = "age",column = "age"),
            @Result(property = "speciality",column = "speciality"),
    })
    Student getStudentInfo(int sid);

    @Select("select * from student")
    @Results({
            @Result(property = "sid",column = "sid"),
            @Result(property = "name",column = "name"),
            @Result(property = "sex",column = "sex"),
            @Result(property = "age",column = "age"),
            @Result(property = "speciality",column = "speciality"),
    })
    List<Student> getStudentAllInfo();

    @Insert("INSERT INTO student( `name`, `sex`, `age`,`speciality`) VALUES ( #{name}, #{sex}, #{age},#{speciality})")
    int insertStudentInfo(@Param("name")String name,@Param("sex")String sex,@Param("age")int age,@Param("speciality")String speciality);
    @Delete("Delete from  student  WHERE sid = #{sid}")
    int deleteStudentInfo(@Param("sid") int sid);
    @Update("UPDATE student SET name = #{name},`sex` = #{sex},age=#{age},speciality=#{speciality} WHERE sid = #{sid}")
    int updateStudentInfo(@Param("name")String name,@Param("sex")String sex,@Param("age")int age,@Param("speciality")String speciality,@Param("sid")int sid);


    @Select("select * from book where bid = #{bid}")
    @Results({
            @Result(property = "bid",column = "bid"),
            @Result(property = "title",column = "title"),
            @Result(property = "desc",column = "desc"),
            @Result(property = "coverImage",column = "cover_image"),
    })
    Book getBookInfo(int bid);

    @Select("select * from book limit #{startNum},#{size}")
    @Results({
            @Result(property = "bid",column = "bid"),
            @Result(property = "title",column = "title"),
            @Result(property = "desc",column = "desc"),
            @Result(property = "coverImage",column = "cover_image"),
    })
    List<Book> getBookAllInfo(@Param("startNum") int pageNum,@Param("size") int size);

    @Update("UPDATE book SET title = #{title},`desc` = #{desc},cover_image=#{coverImage} WHERE bid = #{bid}")
    int updateBookInfo(@Param("title")String title,@Param("desc")String desc,@Param("coverImage")String coverImage,@Param("bid")int bid);

    @Delete("Delete from  book  WHERE bid = #{bid}")
    int deleteBookInfo(@Param("bid")int bid);

    @Insert("INSERT INTO book(`bid`, `title`, `desc`, `cover_image`) VALUES (NULL, #{title}, #{desc}, #{coverImage})")
    int insertBookInfo(@Param("title")String title,@Param("desc")String desc,@Param("coverImage")String coverImage);

    @Select("select * from borrow where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "student",column = "sid",one = @One(select = "getStudentInfo")),
            @Result(property = "book",column = "bid",one = @One(select = "getBookInfo")),
            @Result(property = "date",column = "date")
    })
    Borrow getBorrowInfo(int id);


    @Select("select * from borrow limit #{startNum},#{size} ")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "student",column = "sid",one = @One(select = "getStudentInfo")),
            @Result(property = "book",column = "bid",one = @One(select = "getBookInfo")),
            @Result(property = "date",column = "date")
    })
    List<Borrow> getBorrowAllInfo(@Param("startNum") int pageNum,@Param("size") int size);

    @Insert("INSERT INTO borrow(`id`, `sid`, `bid`, `date`) VALUES (NULL, #{sid}, #{bid}, #{date})")
    int insertBorrowInfo(@Param("sid")int sid, @Param("bid")int bid, @Param("date")Date date);

    @Delete("delete from borrow where id = #{id}")
    int deleteBorrowInfo(int id);
}
