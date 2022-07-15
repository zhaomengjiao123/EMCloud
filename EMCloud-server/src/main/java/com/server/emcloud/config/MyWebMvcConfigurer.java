package com.server.emcloud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * lyx
 * 图片处理
 * */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("file:C:\\Users\\86182\\Desktop\\学习\\暑期实训\\EM\\EMCloud\\EMCloud-server\\src\\main\\resources\\image\\");

    }
}
