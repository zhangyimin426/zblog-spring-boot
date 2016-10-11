package com.zym.api.blog.controller;

import com.zym.common.base.auth.annotation.CheckAuth;
import com.zym.common.base.model.VeriCodeInfo;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Gavin
 * @date 2016-09-29
 */
@RestController
@RequestMapping("/accountRe")
public class AccountRegisterController {

    @Autowired
    private CacheService cacheService;

    @Autowired
    private VeriCodeService veriCodeService;

    /**
     * 获取图片验证码
     *
     * @param commonReqParam 公共请求参数
     * @return JSON
     */
    @CheckAuth
    @RequestMapping(value = "/veriCode", method = RequestMethod.GET)
    public Object getVeriCode(CommonReqParam commonReqParam) {
//        if (reqParam == null) {
//            return JsonResult.fail(GlobalResultStatus.PARAM_MISSING);
//        } else if (StringUtil.isEmpty(reqParam.getAccessToken())) {
//            return JsonResult.fail(GlobalResultStatus.PARAM_ACCESSTOKEN_MISSING);
//        } else if (StringUtil.isEmpty(reqParam.getAppKey())) {
//            return JsonResult.fail(GlobalResultStatus.PARAM_APPKEY_MISSING);
//        } else if (reqParam.getTimestamp() == null) {
//            return JsonResult.fail(GlobalResultStatus.PARAM_TIMESTAMP_MISSING);
//        } else if (reqParam.getSource() == null) {
//            return JsonResult.fail(GlobalResultStatus.PARAM_SOURCE_MISSING);
//        }

        VeriCodeInfo veriCodeInfo = veriCodeService.createVeriCode(commonReqParam.getSource());
        return JsonResult.success(veriCodeInfo);
    }

    /**
     * 根据随机码获取某个验证码是否存在
     * api/account/veriCode/{veriCode}/?randomCode=12345
     * @param veriCode   验证码
     * @param randomCode 随机码
     * @return JSON
     */
    @CheckAuth
    @RequestMapping(value = "/veriCode/{veriCode}", method = RequestMethod.GET)
    public Object checkVeriCode(CommonReqParam commonReqParam, @PathVariable("veriCode") String veriCode, String randomCode) {
        if (StringUtil.isEmptyAny(veriCode, randomCode)) {
            return JsonResult.fail(GlobalResultStatus.PARAM_MISSING);
        }
        if (!veriCodeService.checkVeriCode(veriCode, randomCode, commonReqParam.getSource())) {
            return JsonResult.fail(GlobalResultStatus.VERI_CODE_ERROR);
        }
        veriCodeService.clearVeriCode(randomCode, commonReqParam.getSource());
        return JsonResult.success();
    }

    /**
     * 注册用户
     *
     * @param commonReqParam
     * @param account
     * @param password
     * @param veriCode
     * @param randomCode
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object registerAccount(CommonReqParam commonReqParam, String account, String password, String veriCode, String randomCode) {
        if (StringUtil.isEmptyAny(account, password)) {
            return JsonResult.fail(GlobalResultStatus.PARAM_MISSING);
        }
        if (!veriCodeService.checkVeriCode(veriCode, randomCode, commonReqParam.getSource())) {
            return JsonResult.fail(GlobalResultStatus.VERI_CODE_ERROR);
        }
        veriCodeService.clearVeriCode(randomCode, commonReqParam.getSource());
        return JsonResult.success();
    }
}
