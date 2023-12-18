### 信息管理中心-后端系统

#### 项目介绍

本项目旨在将其作为一个不断完善开发技术和提高自身水平的测试平台。

目前主要基于springboot+Mybatis-Plus实现，由图书管理系统和用户管理系统合并而来，目前已完成的功能有：用户管理、图书管理、学生管理、借阅管理。

当前版本：v1.0.0

#### 组织结构

```
manage-center-backend
├── common -- 工具类、通用代码以及全局异常封装代码
├── user-center -- 用户权限管理模块
├── book-manage -- 图书信息管理模块 | 启动模块

```

#### 技术选型

| 技术             | 说明             | 官网                                                         |
| ---------------- | ---------------- | ------------------------------------------------------------ |
| SpringBoot       | Web应用开发框架  | https://spring.io/projects/spring-boot                       |
| Spring           | 应用开发集成框架 | [Spring Framework --- Spring 框架](https://spring.io/projects/spring-framework) |
| SpringMVC        | Web请求处理框架  | [Spring Web MVC :: Spring Framework](https://docs.spring.io/spring-framework/reference/web/webmvc.html) |
| MyBatis          | ORM框架          | http://www.mybatis.org/mybatis-3/zh/index.html               |
| Mybatis-Plus     | Mybatis增强框架  | [MyBatis-Plus (baomidou.com)](https://baomidou.com/)         |
| MySQL            | 数据库           | [MySQL --- MySQL数据库](https://www.mysql.com/)              |
| MyBatisGenerator | 数据层代码生成器 | http://www.mybatis.org/generator/index.html                  |
| Lombok           | Java语言增强库   | https://github.com/rzwitserloot/lombok                       |
| Maven            | 包管理工具       | [Maven – Welcome to Apache Maven](https://maven.apache.org/) |

#### 项目部署

##### 环境准备

1. JDK版本：11
2. SpringBoot：2.6.13
3. MySQL数据库：5.7.30

##### 本地运行流程

1. 将源码下载到本地
2. 使用IDEA导入项目，自动进行相关依赖导入
3. 在MySQL数据库中将源目录下sql目录中的结构数据和测试数据都导入
4. 在book-manage模块下，resources目录中的`application.yml`文件中设置MySQL数据库连接信息
5. IDEA运行项目