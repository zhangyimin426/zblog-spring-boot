package com.zym.api.blog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Gavin
 * @date 2016-09-26
 */
@ConfigurationProperties(prefix = "business", locations = "classpath:config/business.properties")
public class BusinessConfig {

    private String blogHessianUrl;

    private String cacheHessianUrl;

    public String getCacheHessianUrl() {
        return cacheHessianUrl;
    }

    public void setCacheHessianUrl(String cacheHessianUrl) {
        this.cacheHessianUrl = cacheHessianUrl;
    }

    public String getBlogHessianUrl() {
        return blogHessianUrl;
    }

    public void setBlogHessianUrl(String blogHessianUrl) {
        this.blogHessianUrl = blogHessianUrl;
    }
}
