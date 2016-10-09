package com.zym.common.base.form;

import java.io.Serializable;

/**
 * @author Gavin
 * @date 2016-10-09
 */
public class AccountForm implements Serializable {
    private Integer accountId;

    private Integer status;

    private String phone;

    private String email;

    private Integer regSource;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getRegSource() {
        return regSource;
    }

    public void setRegSource(Integer regSource) {
        this.regSource = regSource;
    }
}
