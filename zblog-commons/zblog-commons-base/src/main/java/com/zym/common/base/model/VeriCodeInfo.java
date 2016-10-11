package com.zym.common.base.model;

import java.io.Serializable;

/**
 * @author Gavin
 * @date 2016-10-10
 */
public class VeriCodeInfo implements Serializable {

    private String randomCode;

    private String veriCode;

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }

    public String getVeriCode() {
        return veriCode;
    }

    public void setVeriCode(String veriCode) {
        this.veriCode = veriCode;
    }
}
