package com.zym.business.blog.config;

import com.zym.business.blog.interceptor.ServiceInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Gavin
 * @date 2016-09-29
 */
@SpringBootConfiguration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ServiceInterceptor()).addPathPatterns("/hessian/**");  //对来自/hessian/** 这个链接来的请求进行拦截
    }
}
