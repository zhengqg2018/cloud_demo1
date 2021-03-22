package com.zqg.consumer_one.config;

import com.zqg.consumer_one.interceptor.ConsumerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: zqg
 * @create: 2020/11/2
 **/
@Slf4j
@Component
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private ConsumerInterceptor consumerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(consumerInterceptor).addPathPatterns("/**");
    }
}