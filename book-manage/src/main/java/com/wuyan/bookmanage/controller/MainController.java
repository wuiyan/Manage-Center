package com.wuyan.bookmanage.controller;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wuyan.bookmanage.empty.Book;
import com.wuyan.bookmanage.empty.Borrow;
import com.wuyan.bookmanage.empty.Student;
import com.wuyan.bookmanage.service.BookService;
import com.wuyan.bookmanage.service.BorrowService;
import com.wuyan.bookmanage.service.StudentService;
import com.wuyan.bookmanage.util.AuthCheck;
import com.wuyan.common.common.ResponseBody;
import com.wuyan.common.common.ResponseCode;
import com.wuyan.common.exception.BusinessException;
import com.wuyan.common.util.ResultUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.wuyan.common.common.ResponseCode.PARAMETER_ERROR;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
public class MainController {
    @RequestMapping(value = {"/"},produces = "application/json;charset=UTF-8")
    public String index(){
        return "欢迎来到我的信息管理系统！";
    }

    @Resource
    private StudentService studentService;

    /**
     * @RequestMapping 接收传入路径的参数是一个数组，可以同时接入多个路径，在路径中使用{}来实现传参数
     * restful风格的url传值只能保持一一对应的关系，缺少某一字段就无法正常访问。
     * @PathVariable 注解用于从url中获取参数，使用时最好指定相应的value值，
     * required参数用于表示当前参数是否必须入参，默认为真
     */
    @RequestMapping(value = {"/student/info/{sid}"},produces = "application/json;charset=UTF-8")
    public ResponseBody<Student> info(@PathVariable(value = "sid") Integer sid, HttpServletRequest httpServletRequest){
        if (!AuthCheck.isLogin(httpServletRequest)){
            throw new BusinessException(ResponseCode.NO_LOGIN);
        }
        System.out.println(sid);
        Student student = studentService.getStudentInfo(sid);
        return ResultUtils.success(student);
    }

    @GetMapping("/student/info")
    public ResponseBody<List<Student>> studentAllInfo(int pageNum,int size ,HttpServletRequest httpServletRequest){
        if (!AuthCheck.isLogin(httpServletRequest)){
            throw new BusinessException(ResponseCode.NO_LOGIN);
        }
        if (pageNum <= 0 || size <= 0){
            throw new BusinessException(PARAMETER_ERROR,"页数和页面大小不能小于零");
        }
        List<Student> studentList = studentService.getStudentAllInfo(pageNum,size);
        return ResultUtils.success(studentList);
    }

    /**
     * 获取用户信息条数
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/student/count")
    public ResponseBody<Long> studentCount(HttpServletRequest httpServletRequest){
        if (!AuthCheck.isLogin(httpServletRequest)){
            throw new BusinessException(ResponseCode.NO_LOGIN);
        }

        long studentCount = studentService.getStudentCount();
        return ResultUtils.success(studentCount);

    }

    /**
     * 更新学生信息
     * @param studentInfo 学生 JSON信息
     * @param httpServletRequest 请求会话
     * @return
     */
    @RequestMapping(value = {"/student/info"},produces = "application/json;charset=UTF-8",method = RequestMethod.PUT)
    public ResponseBody<Integer> updateStudentInfo(@RequestBody String studentInfo,HttpServletRequest httpServletRequest){
        if (!AuthCheck.isLogin(httpServletRequest)){
            throw new BusinessException(ResponseCode.NO_LOGIN);
        }

        System.out.println(studentInfo);
        if (studentInfo.isEmpty()){
            return null;
        }
        Student student = JSON.parseObject(studentInfo, Student.class);
        int result = studentService.updateStudentInfo(student);
        return ResultUtils.success(result);
    }
    @RequestMapping(value = {"/student/info"},produces = "application/json;charset=UTF-8",method = RequestMethod.POST)
    public ResponseBody<Integer> insertStudentInfo(@RequestBody String studentInfo, HttpServletRequest httpServletRequest){
        if (!AuthCheck.isLogin(httpServletRequest)){
            throw new BusinessException(ResponseCode.NO_LOGIN);
        }
        if (studentInfo.isEmpty()){
            return null;
        }
        Student student = JSON.parseObject(studentInfo, Student.class);
        int result = studentService.insertStudentInfo(student);
        return ResultUtils.success(result);
    }

    /**
     * 通过SID删除学生信息
     * @param sid 学生id信息
     * @return
     */
    @RequestMapping(value = "/student/info/{sid}",produces = "application/json;charset=UTF-8",method = RequestMethod.DELETE)
    public ResponseBody<Integer> deleteStudentInfo(@PathVariable(value = "sid") Integer sid, HttpServletRequest httpServletRequest){
        if (!AuthCheck.isLogin(httpServletRequest)){
            throw new BusinessException(ResponseCode.NO_LOGIN);
        }
        if (sid<=0){
            throw new BusinessException(ResponseCode.PARAMETER_ERROR);
        }
        int result = studentService.deleteStudentInfo(sid);
        return ResultUtils.success(result);
    }

    @Resource
    private BookService bookService;    // 引入bookService对象

    /**
     * 通过BID查询指定书籍信息
     * @param bid 书籍ID信息
     */
    @RequestMapping(value = "/book/info/{bid}",produces = "application/json;charset=UTF-8",method = RequestMethod.GET)
    public ResponseBody<Book> BookInfo(@PathVariable(value = "bid") Integer bid, HttpServletRequest httpServletRequest){
        if (!AuthCheck.isLogin(httpServletRequest)){
            throw new BusinessException(ResponseCode.NO_LOGIN);
        }
       Book bookInfo = bookService.getBookInfo(bid);
        return ResultUtils.success(bookInfo);
    }

    /**
     * 分页获取书籍信息
     * @param pageNum 页数
     * @param size 页面大小
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/book/info",produces = "application/json;charset=UTF-8",method = RequestMethod.GET)
    public ResponseBody<List<Book>> AllBookInfo(int pageNum,int size,HttpServletRequest httpServletRequest){
        if (!AuthCheck.isLogin(httpServletRequest)){
            throw new BusinessException(ResponseCode.NO_LOGIN);
        }
        if (pageNum<=0||size<=0){
            throw new BusinessException(PARAMETER_ERROR,"页数和页面大小不能小于零");
        }
        List<Book> bookList = bookService.getBookAllInfo(pageNum, size);
        return ResultUtils.success(bookList);
    }

    /**
     * 获取书籍信息条数
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/book/count")
    public ResponseBody<Long> bookCount(HttpServletRequest httpServletRequest){
        if (!AuthCheck.isLogin(httpServletRequest)){
            throw new BusinessException(ResponseCode.NO_LOGIN);
        }

        long bookCount = bookService.getBookCount();
        return ResultUtils.success(bookCount);

    }

    /**
     * 更新书籍信息
     * @param bookInfo 修改后的JSON格式的书籍信息
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/book/info",produces = "application/json;charset=UTF-8",method = RequestMethod.PUT)
    public ResponseBody<Integer> updateBookInfo(@RequestBody String bookInfo, HttpServletRequest httpServletRequest){
        if (!AuthCheck.isLogin(httpServletRequest)){
            throw new BusinessException(ResponseCode.NO_LOGIN);
        }
        System.out.println(bookInfo);
        Book book = JSON.parseObject(bookInfo, Book.class);
        System.out.println(book.toString());
        int result = bookService.updateBookInfo(book.getTitle(), book.getDesc(), book.getCoverImage(), book.getBid());
        return ResultUtils.success(result);
    }

    @RequestMapping(value = "/book/info/{bid}",produces = "application/json;charset=UTF-8",method = RequestMethod.DELETE)
    public ResponseBody<Integer> deleteBookInfo(@PathVariable(value = "bid")Integer bid, HttpServletRequest httpServletRequest){
        if (!AuthCheck.isLogin(httpServletRequest)){
            throw new BusinessException(ResponseCode.NO_LOGIN);
        }
        System.out.println(bid);
        int result = bookService.deleteBookInfo(bid);
        return ResultUtils.success(result);
    }

    /**
     * 插入书籍信息
     * @param bookInfo
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/book/info",produces = "application/json;charset=UTF-8",method = RequestMethod.POST)
    public ResponseBody<Integer> insertBookInfo(@RequestBody String bookInfo, HttpServletRequest httpServletRequest){
        if (!AuthCheck.isLogin(httpServletRequest)){
            throw new BusinessException(ResponseCode.NO_LOGIN);
        }
        System.out.println(bookInfo);
        Book book = JSON.parseObject(bookInfo, Book.class);
        System.out.println(book.toString());
        int result = bookService.insertBookInfo(book.getTitle(), book.getDesc(), book.getCoverImage());
        return ResultUtils.success(result);

    }


    @Resource
    private BorrowService borrowService;    // 引入borrowService对象
    @RequestMapping(value = "/borrow/info/{id}",produces = "application/json;charset=UTF-8")
    public ResponseBody<Borrow> BorrowInfo(@PathVariable(value = "id") Integer id, HttpServletRequest httpServletRequest){
        if (!AuthCheck.isLogin(httpServletRequest)){
            throw new BusinessException(ResponseCode.NO_LOGIN);
        }
        Borrow borrow = borrowService.getBorrowInfo(id);
        return ResultUtils.success(borrow);
    }

    /**
     * 获取借阅信息列表
     * @param pageNum 页数
     * @param size 页面大小
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/borrow/info",produces = "application/json;charset=UTF-8",method = RequestMethod.GET)
    public ResponseBody<List<Borrow>> AllBorrowInfo(int pageNum,int size,HttpServletRequest httpServletRequest){
        if (!AuthCheck.isLogin(httpServletRequest)){
            throw new BusinessException(ResponseCode.NO_LOGIN);
        }
        if (pageNum<=0||size<=0){
            throw new BusinessException(ResponseCode.PARAMETER_ERROR,"页面大小和请求页数不能小于0");
        }
        List<Borrow> borrowList = borrowService.getBorrowAllInfo(pageNum,size);
//        return JSON.toJSONStringWithDateFormat(borrowList,"yyyy-MM-dd", SerializerFeature.DisableCircularReferenceDetect);
        return ResultUtils.success(borrowList);

    }
    /**
     * 获取借阅信息条数
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/borrow/count")
    public ResponseBody<Long> borrowCount(HttpServletRequest httpServletRequest){
        if (!AuthCheck.isLogin(httpServletRequest)){
            throw new BusinessException(ResponseCode.NO_LOGIN);
        }

        long borrowCount = borrowService.getBorrowCount();
        return ResultUtils.success(borrowCount);

    }

    @RequestMapping(value = "/borrow/info",produces = "application/json;charset=UTF-8",method = RequestMethod.POST)
    public ResponseBody<Integer> insertBorrowInfo(@RequestBody String borrowInfo, HttpServletRequest httpServletRequest){
        if (!AuthCheck.isLogin(httpServletRequest)){
            throw new BusinessException(ResponseCode.NO_LOGIN);
        }
        System.out.println(borrowInfo);
        JSONObject jsonObject = JSONObject.parseObject(borrowInfo);
//        System.out.println(jsonObject.toJSONString());
        System.out.println(jsonObject.getString("sid"));
        System.out.println(jsonObject.getString("date"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date datetime = dateFormat.parse(jsonObject.getString("date"));
            int result = borrowService.insertBorrowInfo(Integer.parseInt(jsonObject.getString("sid")), Integer.parseInt(jsonObject.getString("bid")), datetime);
            return ResultUtils.success(result);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    @RequestMapping(value = "/borrow/info/{id}",produces = "application/json;charset=UTF-8",method = RequestMethod.DELETE)
    public ResponseBody<Integer> deleteBorrowInfo(@PathVariable(value = "id")Integer id, HttpServletRequest httpServletRequest){
        if (!AuthCheck.isLogin(httpServletRequest)){
            throw new BusinessException(ResponseCode.NO_LOGIN);
        }
        System.out.println(id);
        int result = borrowService.deleteBorrowInfo(id);
        return ResultUtils.success(result);

    }


}
