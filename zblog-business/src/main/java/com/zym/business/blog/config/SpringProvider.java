package com.zym.business.blog.config;

import com.zym.common.base.service.BlogService;
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

    /**
     * 暴露博客相关服务接口
     *
     * @return
     */
    @Bean(name = "/hessian/blogService")
    public HessianServiceExporter accountBlogService() {
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
    public HessianServiceExporter accountCacheService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(cacheService);
        exporter.setServiceInterface(CacheService.class);
        return exporter;
    }
}
