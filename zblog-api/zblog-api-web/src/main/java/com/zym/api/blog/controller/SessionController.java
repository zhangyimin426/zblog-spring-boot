package com.zym.api.blog.controller;

import com.zym.common.base.auth.annotation.CheckAuth;
import com.zym.common.base.constant.BaseConstant;
import com.zym.common.base.model.Account;
import com.zym.common.base.model.base.CommonReqParam;
import com.zym.common.base.service.AccountService;
import com.zym.common.base.service.AppInfoService;
import com.zym.common.base.service.AuthService;
import com.zym.common.base.service.TokenService;
import com.zym.common.base.statuscode.GlobalResultStatus;
import com.zym.common.base.utils.JsonResult;
import com.zym.common.base.utils.StringUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 管理后台登录和登出
 *
 * @author Gavin
 * @date 2016-10-09
 */
@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthService authService;

    @Autowired
    private AppInfoService appInfoService;

    @Autowired
    private TokenService tokenService;

    /**
     * 创建会话（登录）
     *
     * @param commonReqParam 公共请求参数
     * @param account        手机号码或邮箱
     * @param password       密码
     * @param session        会话
     * @return
     */
    @CheckAuth
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object login(CommonReqParam commonReqParam, String account, String password, HttpSession session) {
        if (StringUtil.isEmptyAll(account, password)) {
            return JsonResult.fail(GlobalResultStatus.PARAM_MISSING);
        }

        //查询帐号信息
        Account accountResult = accountService.getAccount(account);
        if (accountResult == null) {
            return JsonResult.fail(GlobalResultStatus.ACCOUNT_NOT_REGISTERED);
        }

        //检查密码安全
        if (!DigestUtils.md5Hex(accountResult.getPassword() + appInfoService.getByAppKey(commonReqParam.getAppKey())).equals(password)) {
            return JsonResult.fail(GlobalResultStatus.PASSWORD_ERROR);
        }
        session.setAttribute(BaseConstant.ADMIN_SESSION, accountResult);

//        // start 登录记录历史
//        try {
//            recordLoginHistory(appId, request, accountResult.getAccountId());
//        } catch (Exception e) {
//            LOGGER.info("账号【" + account + "】【" + appId + "】在login方法记录登录日志失败", e);
//        }
//        // end

        return JsonResult.success(accountResult);
    }

    /**
     * 销毁当前会话（登出）
     *
     * @param session 会话
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public Object logout(HttpSession session) {
        session.removeAttribute(BaseConstant.ADMIN_SESSION);
        return JsonResult.success();
    }

}
