package com.zym.common.base.model;

import java.io.Serializable;

/**
 * @author Gavin
 * @date 2016-10-09
 */
public class AppInfo implements Serializable {
    private String appKey;
    private String appSecret;
    private Integer status;//0-无效，1-有效
    private Integer belongs;//0-H5，1-Android，2-IOS

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBelongs() {
        return belongs;
    }

    public void setBelongs(Integer belongs) {
        this.belongs = belongs;
    }
}
