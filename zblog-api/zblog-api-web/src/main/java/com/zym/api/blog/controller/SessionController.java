package com.zym.api.blog.controller;

import com.zym.common.base.auth.annotation.CheckAuth;
import com.zym.common.base.constant.BaseConstant;
import com.zym.common.base.model.Account;
import com.zym.common.base.model.LoginHistory;
import com.zym.common.base.model.base.CommonReqParam;
import com.zym.common.base.service.*;
import com.zym.common.base.statuscode.GlobalResultStatus;
import com.zym.common.base.utils.DateUtil;
import com.zym.common.base.utils.JsonResult;
import com.zym.common.base.utils.RequestUtil;
import com.zym.common.base.utils.StringUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

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
    private AppInfoService appInfoService;

    @Autowired
    private LoginHistoryService loginHistoryService;

    private static final Logger log = LoggerFactory.getLogger(SessionController.class);

    /**
     * 创建会话（登录）
     *
     * @param commonReqParam 公共请求参数
     * @param account        手机号码或邮箱
     * @param password       密码
     * @param request        请求
     * @return
     */
    @CheckAuth
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object login(CommonReqParam commonReqParam, String account, String password, HttpServletRequest request) {

        //查询帐号信息
        Account accountResult = accountService.getByAccount(account);
        if (accountResult == null) {
            return JsonResult.fail(GlobalResultStatus.ACCOUNT_NOT_REGISTERED);
        }

        //检查密码安全
        if (!DigestUtils.md5Hex(accountResult.getPassword() + appInfoService.getByAppKey(commonReqParam.getAppKey()).getAppSecret()).equals(password)) {
            return JsonResult.fail(GlobalResultStatus.PASSWORD_ERROR);
        }
        request.getSession().setAttribute(BaseConstant.ADMIN_SESSION, accountResult);

        try {
            recordLoginHistory(request, accountResult.getAccountId());
        } catch (Exception e) {
            log.info("账号【" + account + "】记录登录日志失败", e);
        }

        return JsonResult.success(accountResult);
    }

    /**
     * 记录登录日志
     *
     * @param request
     * @param accountId
     */
    private void recordLoginHistory(HttpServletRequest request, Integer accountId) {
        LoginHistory history = new LoginHistory();
        history.setAccountId(accountId);
        history.setLoginTime(DateUtil.getLocalDate(new Date()));
        history.setRemoteIp(RequestUtil.getRemoteAddr(request));
        loginHistoryService.insert(history);
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
