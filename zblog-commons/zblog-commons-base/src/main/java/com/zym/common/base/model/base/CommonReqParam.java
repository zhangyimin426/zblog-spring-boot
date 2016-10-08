package com.zym.common.base.model.base;

import java.io.Serializable;

/**
 * @author Gavin
 * @date 2016-09-30
 */
public class CommonReqParam implements Serializable {
    private String appKey;

    private int sex;

    private String accessToken;//有些接口不一定要传

    private Long timestamp;

    private Integer source;//0-H5，1-Android，2-IOS

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }
}
