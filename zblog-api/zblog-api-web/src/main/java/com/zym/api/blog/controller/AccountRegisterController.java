package com.zym.api.blog.controller;

import com.zym.common.base.auth.annotation.CheckAuth;
import com.zym.common.base.model.base.CommonReqParam;
import com.zym.common.base.service.VeriCodeService;
import com.zym.common.base.service.cache.CacheService;
import com.zym.common.base.statuscode.GlobalResultStatus;
import com.zym.common.base.utils.JsonResult;
import com.zym.common.base.utils.RandomUtil;
import com.zym.common.base.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Gavin
 * @date 2016-09-29
 */
@RequestMapping("/account")
public class AccountRegisterController {

    @Autowired
    private CacheService cacheService;

    @Autowired
    private VeriCodeService veriCodeService;

    /**
     * 获取图片验证码
     *
     * @param reqParam 请求参数
     * @return JSON
     */
    @CheckAuth
    @ResponseBody
    @RequestMapping(value = "/veriCode", method = RequestMethod.GET)
    public Object getVeriCode(CommonReqParam reqParam) {
        if (reqParam == null) {
            return JsonResult.fail(GlobalResultStatus.PARAM_MISSING);
        }
        //产生6位验证码
        String veriCode = RandomUtil.getNumbers(6);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String randomCode = RandomUtil.getUUID();
        //一个随机码对应一个验证码
        cacheService.setSerializer("PicRandomCode:" + randomCode + "source:" + reqParam.getSource(), veriCode, 1800L);
        resultMap.put("veriCode", veriCode);
        resultMap.put("randomCode", randomCode);
        return JsonResult.success(resultMap);
    }

    /**
     * 根据随机码获取某个验证码是否存在
     *
     * @param veriCode   验证码
     * @param randomCode 随机码
     * @return JSON
     */
    @CheckAuth
    @ResponseBody
    @RequestMapping(value = "/veriCode/{veriCode}", method = RequestMethod.GET)
    public Object checkVeriCode(String appKey, @PathVariable("veriCode") String veriCode, String randomCode, String source) {
        if (StringUtil.isEmptyAny(appKey, veriCode, randomCode, source)) {
            return JsonResult.fail(GlobalResultStatus.PARAM_MISSING);
        }
        if (!veriCodeService.checkVeriCode(veriCode, randomCode, source)) {
            return JsonResult.fail(GlobalResultStatus.VERI_CODE_ERROR);
        }
        veriCodeService.clearVeriCode(veriCode, randomCode, source);
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object registerAccount(String appKey, String account, String password, String source) {
        if (StringUtil.isEmptyAny(appKey, account, password, source)) {
            return JsonResult.fail(GlobalResultStatus.PARAM_MISSING);
        }
        if (!veriCodeService.checkVeriCode(veriCode, randomCode, source)) {
            return JsonResult.fail(GlobalResultStatus.VERI_CODE_ERROR);
        }
        veriCodeService.clearVeriCode(veriCode, randomCode, source);
        return JsonResult.success();
    }
}
