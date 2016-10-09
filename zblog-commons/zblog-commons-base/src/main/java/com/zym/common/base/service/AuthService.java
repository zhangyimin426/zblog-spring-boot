package com.zym.common.base.service;

import com.zym.common.base.auth.Auth;

/**
 * @author Gavin
 * @date 2016-09-29
 */
public interface AuthService {
    /**
     * 创建授权信息
     *
     * @param accountId 用户账号标识
     * @return 授权信息
     */
    Auth createAuth(Integer accountId, String userAgent, String appKey);
}
