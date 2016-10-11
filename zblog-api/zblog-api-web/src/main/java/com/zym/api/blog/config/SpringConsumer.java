package com.zym.api.blog.config;

import com.caucho.hessian.client.HessianProxyFactory;
import com.zym.common.base.exception.blog.BlogBusinessException;
import com.zym.common.base.service.*;
import com.zym.common.base.service.cache.CacheService;
import com.zym.common.base.statuscode.ResultStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author Gavin
 * @date 2016-09-27
 */
@SpringBootConfiguration
@EnableConfigurationProperties(BusinessConfig.class)
public class SpringConsumer {

    private static final Logger log = LoggerFactory.getLogger(SpringConsumer.class);

    @Autowired
    private BusinessConfig businessConfig;

    @Bean(name = "/blogService")
    public BlogService getBlogService() {

        BlogService blogService = null;
        try {
            String url = businessConfig.getBlogHessianUrl();
            HessianProxyFactory factory = new HessianProxyFactory();
            factory.setReadTimeout(6000);
            factory.setConnectTimeout(6000);
            factory.setOverloadEnabled(true);
            blogService = (BlogService) factory.create(BlogService.class, url);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BlogBusinessException(new ResultStatus(500, "远程获取blogService异常"));
        }
        return blogService;
    }

    @Bean(name = "/cacheService")
    public CacheService getCacheService() {

        CacheService cacheService = null;
        try {
            String url = businessConfig.getCacheHessianUrl();
            HessianProxyFactory factory = new HessianProxyFactory();
            factory.setReadTimeout(6000);
            factory.setConnectTimeout(6000);
            factory.setOverloadEnabled(true);
            cacheService = (CacheService) factory.create(CacheService.class, url);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BlogBusinessException(new ResultStatus(500, "远程获取cacheService异常"));
        }
        return cacheService;
    }

    @Bean(name = "/accountService")
    public AccountService getAccountService() {

        AccountService accountService = null;
        try {
            String url = businessConfig.getAccountHessianUrl();
            HessianProxyFactory factory = new HessianProxyFactory();
            factory.setReadTimeout(6000);
            factory.setConnectTimeout(6000);
            factory.setOverloadEnabled(true);
            accountService = (AccountService) factory.create(AccountService.class, url);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BlogBusinessException(new ResultStatus(500, "远程获取accountService异常"));
        }
        return accountService;
    }

    @Bean(name = "/appInfoService")
    public AppInfoService getAppInfoService() {

        AppInfoService appInfoService = null;
        try {
            String url = businessConfig.getAppInfoHessianUrl();
            HessianProxyFactory factory = new HessianProxyFactory();
            factory.setReadTimeout(6000);
            factory.setConnectTimeout(6000);
            factory.setOverloadEnabled(true);
            appInfoService = (AppInfoService) factory.create(AppInfoService.class, url);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BlogBusinessException(new ResultStatus(500, "远程获取appInfoService异常"));
        }
        return appInfoService;
    }

    @Bean(name = "/loginHistoryService")
    public LoginHistoryService getLoginHistoryService() {

        LoginHistoryService loginHistoryService = null;
        try {
            String url = businessConfig.getLoginHistoryHessianUrl();
            HessianProxyFactory factory = new HessianProxyFactory();
            factory.setReadTimeout(6000);
            factory.setConnectTimeout(6000);
            factory.setOverloadEnabled(true);
            loginHistoryService = (LoginHistoryService) factory.create(LoginHistoryService.class, url);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BlogBusinessException(new ResultStatus(500, "远程获取loginHistoryService异常"));
        }
        return loginHistoryService;
    }

    @Bean(name = "/userService")
    public UserService getUserService() {

        UserService userService = null;
        try {
            String url = businessConfig.getUserHessianUrl();
            HessianProxyFactory factory = new HessianProxyFactory();
            factory.setReadTimeout(6000);
            factory.setConnectTimeout(6000);
            factory.setOverloadEnabled(true);
            userService = (UserService) factory.create(UserService.class, url);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BlogBusinessException(new ResultStatus(500, "远程获取userService异常"));
        }
        return userService;
    }

    @Bean(name = "/veriCodeService")
    public VeriCodeService getVeriCodeService() {

        VeriCodeService veriCodeService = null;
        try {
            String url = businessConfig.getVeriCodeHessianUrl();
            HessianProxyFactory factory = new HessianProxyFactory();
            factory.setReadTimeout(6000);
            factory.setConnectTimeout(6000);
            factory.setOverloadEnabled(true);
            veriCodeService = (VeriCodeService) factory.create(VeriCodeService.class, url);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BlogBusinessException(new ResultStatus(500, "远程获取veriCodeService异常"));
        }
        return veriCodeService;
    }
}
