package com.wuyan.bookmanage.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
@ComponentScans({
        @ComponentScan("com.wuyan.bookmanage.controller"),
        @ComponentScan("com.wuyan.bookmanage.config"),
        @ComponentScan("com.wuyan.bookmanage.service")
}
)
@EnableWebMvc
public class MvcServletConfig implements WebMvcConfigurer {
    // 配置模板解析器
    @Bean
    @Primary
    public SpringResourceTemplateResolver springResourceTemplateResolver(){
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setSuffix(".html");
        resolver.setPrefix("/template/");
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }

    // 配置模板引擎Bean,此时自动注入的其实是上面的模板解析器，@Autowired会优先按照类型去寻找，最终找到实现了此接口的类，即SpringResourceTemplateResolver，SpringResourceTemplateResolver是ITemplateResolver接口的实现类
    @Bean
    public SpringTemplateEngine springTemplateEngine(@Autowired ITemplateResolver iTemplateResolver){
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(iTemplateResolver);
        return engine;
    }

    // 使用ThymeleafViewResolver作为视图解析器，用于解析HTML页面
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver (@Autowired SpringTemplateEngine springTemplateEngine){
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setOrder(1);
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateEngine(springTemplateEngine);
        return resolver;
    }

    // 设置静态资源映射

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
}
