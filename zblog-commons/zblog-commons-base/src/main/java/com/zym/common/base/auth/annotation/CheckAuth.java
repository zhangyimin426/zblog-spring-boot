package com.zym.common.base.auth.annotation;




import com.zym.common.base.auth.constant.AuthMethod;
import com.zym.common.base.auth.constant.AuthParam;
import com.zym.common.base.auth.constant.AuthScheme;

import java.lang.annotation.*;

/**
 * 检查授权信息的注解类
 * @author Gavin
 * @date 2016-09-02
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckAuth {
    /**
     * 需要检查的授权参数，必定检查appId、timestamp两个授权参数。
     *
     * @return 授权参数
     */
    AuthParam[] param() default {};

    /**
     * 需要检查的请求Scheme，不填则默认通过。
     *
     * @return 请求Scheme
     */
    AuthScheme[] scheme() default {};

    /**
     * 需要检查的请求方法，不填则默认通过。
     *
     * @return 请求方法
     */
    AuthMethod[] method() default {};
}
