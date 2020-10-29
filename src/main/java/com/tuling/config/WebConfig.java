package com.tuling.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@MapperScan("com.tuling.dao")
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("leftRequire").setViewName("leftRequire");
        registry.addViewController("Order_ytb_list").setViewName("Order_ytb_list");
        registry.addViewController("print_order_detail").setViewName("print_order_detail");
        registry.addViewController("bianzhicaigoujihua").setViewName("bianzhicaigoujihua");
        registry.addViewController("Project_list").setViewName("Project_list");
        registry.addViewController("xjfatz_xjfamx").setViewName("xjfatz_xjfamx");
    }
}
