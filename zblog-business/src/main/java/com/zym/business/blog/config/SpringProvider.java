package com.zym.business.blog.config;

import com.zym.common.base.service.*;
import com.zym.common.base.service.cache.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianServiceExporter;

/**
 * @author Gavin
 * @date 2016-09-27
 */
@SpringBootConfiguration
public class SpringProvider {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AppInfoService appInfoService;

    @Autowired
    private LoginHistoryService loginHistoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private VeriCodeService veriCodeService;

    /**
     * 暴露博客相关服务接口
     *
     * @return
     */
    @Bean(name = "/hessian/blogService")
    public HessianServiceExporter exposeBlogService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(blogService);
        exporter.setServiceInterface(BlogService.class);
        return exporter;
    }

    /**
     * 暴露缓存服务接口
     *
     * @return
     */
    @Bean(name = "/hessian/cacheService")
    public HessianServiceExporter exposeCacheService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(cacheService);
        exporter.setServiceInterface(CacheService.class);
        return exporter;
    }

    /**
     * 暴露账号服务接口
     *
     * @return
     */
    @Bean(name = "/hessian/accountService")
    public HessianServiceExporter exposeAccountService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(accountService);
        exporter.setServiceInterface(AccountService.class);
        return exporter;
    }

    /**
     * 暴露appInfo服务接口
     *
     * @return
     */
    @Bean(name = "/hessian/appInfoService")
    public HessianServiceExporter exposeAppInfoService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(appInfoService);
        exporter.setServiceInterface(AppInfoService.class);
        return exporter;
    }

    /**
     * 暴露登录记录日志服务接口
     *
     * @return
     */
    @Bean(name = "/hessian/loginHistoryService")
    public HessianServiceExporter exposeLoginHistoryService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(loginHistoryService);
        exporter.setServiceInterface(LoginHistoryService.class);
        return exporter;
    }

    /**
     * 暴露用户服务接口
     *
     * @return
     */
    @Bean(name = "/hessian/userService")
    public HessianServiceExporter exposeUserService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(userService);
        exporter.setServiceInterface(UserService.class);
        return exporter;
    }

    /**
     * 暴露用户服务接口
     *
     * @return
     */
    @Bean(name = "/hessian/veriCodeService")
    public HessianServiceExporter exposeVeriCodeService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(veriCodeService);
        exporter.setServiceInterface(VeriCodeService.class);
        return exporter;
    }
}
