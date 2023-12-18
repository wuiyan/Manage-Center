//package com.wuyan.bookmanage.config;
//
//import org.apache.ibatis.datasource.pooled.PooledDataSource;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//
//
//import javax.sql.DataSource;
//import java.util.Objects;
//
//@Configuration
//@MapperScan(basePackages = "com.wuyan.bookmanage.mapper",sqlSessionTemplateRef = "bookSqlSessionTemplate")
//@ComponentScan("com.wuyan.bookmanage.service")
//@PropertySource("classpath:database.properties") // 加载资源文件
//public class BookMybatisConfig {
////    @Bean   // 此注解一般只能用到配置类中，相当于xml文件中的配置Bean对象的方式
////    public DataSource dataSource(){
////        // 创建池化数据源，类似xml的设置数据库
////        return new PooledDataSource("com.mysql.cj.jdbc.Driver",
////                "jdbc:mysql://127.0.0.1:3306/book_manage",
////                "root","123456");
////    }
//    @Value("${db.driver}")  // 引入相应属性
//    private String driver;
//    @Value("${db.url}")
//    private String url;
//    @Value("${db.username}")
//    private String username;
//    @Value("${db.password}")
//    private String password;
//    @Bean(name = "dataSourceBook")
//    public DataSource dataSource(){
//        return new PooledDataSource(driver,url,username,password);
//    }
//    @Bean(name = "sqlSessionFactoryBeanBook")    // 配置SqlSessionFactory，基于之前配置的数据源，通过工厂可以获得相应的SqlSession连接
//    public SqlSessionFactoryBean sqlSessionFactoryBean(@Autowired DataSource dataSource){
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        factoryBean.setDataSource(dataSource);
//        return factoryBean;
//    }
//    @Bean(name = "bookSqlSessionTemplate")
//    public SqlSessionTemplate userSqlSessionTemplate(@Qualifier("sqlSessionFactoryBeanBook") @Autowired SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
//        return new SqlSessionTemplate(Objects.requireNonNull(sqlSessionFactoryBean.getObject()));
//    }
//}
