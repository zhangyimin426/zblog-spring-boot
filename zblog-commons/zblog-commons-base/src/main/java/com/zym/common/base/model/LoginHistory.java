package com.zym.common.base.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Gavin
 * @date 2016-10-10
 */
public class LoginHistory implements Serializable {
    private Integer accountId;

    private Date loginTime;

    private String remoteIp;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }
}
