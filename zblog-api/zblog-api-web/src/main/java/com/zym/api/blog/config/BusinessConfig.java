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

    private String accountHessianUrl;

    private String appInfoHessianUrl;

    private String loginHistoryHessianUrl;

    private String userHessianUrl;

    private String veriCodeHessianUrl;

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

    public String getAccountHessianUrl() {
        return accountHessianUrl;
    }

    public void setAccountHessianUrl(String accountHessianUrl) {
        this.accountHessianUrl = accountHessianUrl;
    }

    public String getAppInfoHessianUrl() {
        return appInfoHessianUrl;
    }

    public void setAppInfoHessianUrl(String appInfoHessianUrl) {
        this.appInfoHessianUrl = appInfoHessianUrl;
    }

    public String getLoginHistoryHessianUrl() {
        return loginHistoryHessianUrl;
    }

    public void setLoginHistoryHessianUrl(String loginHistoryHessianUrl) {
        this.loginHistoryHessianUrl = loginHistoryHessianUrl;
    }

    public String getUserHessianUrl() {
        return userHessianUrl;
    }

    public void setUserHessianUrl(String userHessianUrl) {
        this.userHessianUrl = userHessianUrl;
    }

    public String getVeriCodeHessianUrl() {
        return veriCodeHessianUrl;
    }

    public void setVeriCodeHessianUrl(String veriCodeHessianUrl) {
        this.veriCodeHessianUrl = veriCodeHessianUrl;
    }
}
