package com.zym.common.base.auth;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Gavin
 * @date 2016-09-29
 */
public class Auth implements Serializable {

    private Integer accountId;
    private Integer userId;
    private Long timestamp;
    private String sign;
    private String accessToken;
    private Integer accessTokenExpires;
    private String refreshToken;
    private Integer refreshTokenExpires;
    private String phone;
    private String email;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getAccessTokenExpires() {
        return accessTokenExpires;
    }

    public void setAccessTokenExpires(Integer accessTokenExpires) {
        this.accessTokenExpires = accessTokenExpires;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Integer getRefreshTokenExpires() {
        return refreshTokenExpires;
    }

    public void setRefreshTokenExpires(Integer refreshTokenExpires) {
        this.refreshTokenExpires = refreshTokenExpires;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    private Date loginTime;
    private String appVersion;


}
