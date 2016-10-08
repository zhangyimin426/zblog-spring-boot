package com.zym.api.blog.config;

import com.caucho.hessian.client.HessianProxyFactory;
import com.zym.common.base.exception.blog.BlogBusinessException;
import com.zym.common.base.service.BlogService;
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

}
