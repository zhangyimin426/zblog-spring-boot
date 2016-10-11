package com.zym.common.base.service;

import com.zym.common.base.model.VeriCodeInfo;

/**
 * @author Gavin
 * @date 2016-09-29
 */
public interface VeriCodeService {

    /**
     * 产生一个验证码
     *
     * @param source  来源
     * @return 验证码
     */
    VeriCodeInfo createVeriCode(Integer source);

    /**
     * 检查验证码
     *
     * @param clientVeriCode   客户端验证码
     * @param clientRandomCode 客户端随机码
     * @param source           来源
     * @return true检查通过
     */
    boolean checkVeriCode(String clientVeriCode, String clientRandomCode, Integer source);

    /**
     * 检查验证码是否过期
     *
     * @param clientVeriCode   客户端验证码
     * @param clientRandomCode 客户端随机码
     * @param source           来源
     * @return true检查通过
     */
    boolean checkVeriCodeTime(String clientVeriCode, String clientRandomCode, Integer source);

    /**
     * 清除验证码
     *
     * @param clientRandomCode 客户端随机码
     * @param source           来源
     */
    void clearVeriCode(String clientRandomCode, Integer source);
}
