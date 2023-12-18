package com.wuyan.usercenter;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.wuyan.usercenter.mapper.UserMapper;
import com.wuyan.usercenter.model.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@SpringBootTest
public class SampleTest {
//    @Resource
//    private UserMapper userMapper;
//
//    @Test
//    public void testSelect() {
//        System.out.println(("----- selectAll method test ------"));
//        List<User> userList = userMapper.selectList(null);
//        Assert.isTrue(5 == userList.size(), "");
//        userList.forEach(System.out::println);
//    }
//    @Test
//    void testIdea(){
//        String pattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
//        String match = "sd !sd```";
//        Matcher matcher = Pattern.compile(pattern).matcher(match);
//        System.out.println(matcher.find());
//        System.out.println(matcher.group());
//    }
    @Test
    void classPath() throws IOException {
//classpath: 表示加载当前类路径中所有匹配的资源
        Resource resource = new PathMatchingResourcePatternResolver().getResource("classpath:mapper/*.xml");
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:*.xml");
        for(Resource r : resources){
            System.out.println(r.getFilename()); //文件名
            System.out.println(r.getURL().getPath()); //文件绝对路径
            System.out.println(r.getFile()); //File对象
        }
        System.out.println(new PathMatchingResourcePatternResolver().getResource("classpath:mapper/*.xml").getFilename());
    }

}
