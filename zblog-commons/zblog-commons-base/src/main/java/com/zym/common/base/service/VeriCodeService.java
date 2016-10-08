package com.zym.common.base.service;

/**
 * @author Gavin
 * @date 2016-09-29
 */
public interface VeriCodeService {

    /**
     * 检查验证码
     *
     * @param clientVeriCode   客户端验证码
     * @param clientRandomCode 客户端验证码
     * @param source           来源
     * @return true检查通过
     */
    boolean checkVeriCode(String clientVeriCode, String clientRandomCode, String source);

    /**
     * 检查验证码是否过期
     *
     * @param clientVeriCode   客户端验证码
     * @param clientRandomCode 客户端验证码
     * @param source           来源
     * @return true检查通过
     */
    boolean checkVeriCodeTime(String clientVeriCode, String clientRandomCode, String source);

    /**
     * 清除验证码
     *
     * @param clientVeriCode   客户端验证码
     * @param clientRandomCode 客户端验证码
     * @param source           来源
     */
    void clearVeriCode(String clientVeriCode, String clientRandomCode, String source);
}
