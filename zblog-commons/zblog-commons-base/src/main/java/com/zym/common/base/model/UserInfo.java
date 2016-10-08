package com.zym.common.base.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息，包括账号相关信息（除密码）
 * @author Gavin
 * @date 2016-09-29
 */
public class UserInfo implements Serializable {

    private Integer accountId;

    private String phone;

    private String email;

    private Integer regSource;

    private Integer userId;

    private Integer status;

    private String userName;

    private String nickName;

    private String profile;

    private Integer sex;

    private Date birthday;

    private String avatar;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
