package com.zym.api.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author Gavin
 * @date 2016-09-26
 */
@SpringBootApplication
@ServletComponentScan
public class BlogApiWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApiWebApplication.class, args);
    }
}
