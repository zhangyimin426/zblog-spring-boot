package com.zym.business.blog.service;

import com.zym.common.base.model.VeriCodeInfo;
import com.zym.common.base.service.VeriCodeService;
import com.zym.common.base.service.cache.CacheService;
import com.zym.common.base.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Gavin
 * @date 2016-10-10
 */
@Service("veriCodeService")
public class VeriCodeServiceImpl implements VeriCodeService {

    @Autowired
    private CacheService cacheService;

    @Override
    public VeriCodeInfo createVeriCode(Integer source) {
        VeriCodeInfo veriCodeInfo = new VeriCodeInfo();
        //产生6位验证码
        String veriCode = RandomUtil.getNumbers(6);
        String randomCode = RandomUtil.getUUID();
        //一个随机码对应一个验证码信息放入缓存
        cacheService.setSerializer("PicRandomCode:" + randomCode + "source:" + source, veriCodeInfo, 1800L);
        veriCodeInfo.setVeriCode(veriCode);
        veriCodeInfo.setRandomCode(randomCode);
        return veriCodeInfo;
    }

    @Override
    public boolean checkVeriCode(String clientVeriCode, String clientRandomCode, Integer source) {

        return clientVeriCode.equals(cacheService.getSerializer("PicRandomCode:" + clientRandomCode + "source:" + source, String.class));
    }

    @Override
    public boolean checkVeriCodeTime(String clientVeriCode, String clientRandomCode, Integer source) {

        return false;
    }

    @Override
    public void clearVeriCode(String clientRandomCode, Integer source) {
        cacheService.delete("PicRandomCode:" + clientRandomCode + "source:" + source);
    }
}
