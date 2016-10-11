package com.zym.common.base.service;

import com.zym.common.base.auth.Auth;

/**
 * 用户app接口token服务
 * @author Gavin
 * @date 2016-09-29
 */
public interface TokenService {


    /**
     * 设置Token(帐号登录)信息
     *
     * @param auth
     */
    void setAuth(Auth auth);


    void refreshAuth(Auth auth);

    /**
     * 获取帐号授权信息
     *
     * @param accessToken
     * @param appKey
     * @return 授权信息
     */
    Auth getAuthByAccessToken(String accessToken, String appKey);

    /**
     * 获取过期授权信息
     *
     * @param ExpiresToken
     * @param appKey
     * @return
     */
    Auth getAuthByExpiresToken(String ExpiresToken, String appKey);

    /**
     * 获取刷新授权信息
     *
     * @param refreshToken
     * @param appKey
     * @return
     */
    Auth getAuthByRefreshTokenn(String refreshToken, String appKey);

    /**
     * 获取帐号标识
     *
     * @param accessToken
     * @param appKey
     * @return 帐号标识
     */
    Integer getUserIdByAccessToken(String accessToken, String appKey);

    /**
     * 获取帐号标识
     *
     * @param refreshToken
     * @param appKey
     * @return 帐号标识
     */
    Integer getUserIdByRefreshToken(String refreshToken, String appKey);

    /**
     * 获取帐号标识
     *
     * @param expiresToken
     * @param appKey
     * @return
     */
    Integer getUserIdByExpiresToken(String expiresToken, String appKey);

}
