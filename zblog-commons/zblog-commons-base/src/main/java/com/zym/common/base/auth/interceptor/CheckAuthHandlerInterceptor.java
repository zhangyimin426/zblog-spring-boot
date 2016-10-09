package com.zym.common.base.auth.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zym.common.base.auth.Auth;
import com.zym.common.base.auth.annotation.CheckAuth;
import com.zym.common.base.auth.constant.AuthMethod;
import com.zym.common.base.auth.constant.AuthParam;
import com.zym.common.base.auth.constant.AuthScheme;
import com.zym.common.base.constant.BaseConstant;
import com.zym.common.base.model.Account;
import com.zym.common.base.model.base.CommonReqParam;
import com.zym.common.base.service.AppInfoService;
import com.zym.common.base.service.TokenService;
import com.zym.common.base.statuscode.GlobalResultStatus;
import com.zym.common.base.statuscode.ResultStatus;
import com.zym.common.base.utils.DateUtil;
import com.zym.common.base.utils.JsonResult;
import com.zym.common.base.utils.StringUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Gavin
 * @date 2016-09-02
 */
public class CheckAuthHandlerInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(CheckAuthHandlerInterceptor.class);

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AppInfoService appInfoService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果是静态资源，放过
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }
        CommonReqParam commonReqParam = packagingCommonReqParam(request);
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        CheckAuth auth = handlerMethod.getMethodAnnotation(CheckAuth.class);

        if (!checkAuthMethod(request, response, auth)) {
            return false;
        }

        if (!checkAuthScheme(request, response, auth)) {
            return false;
        }

        if (!checkCommonReqParamEmpty(request, response, commonReqParam)) {
            return false;
        }

        //如果是h5请求端那么就不用检测token之类的
        if (commonReqParam.getSource() == BaseConstant.SOURCE_H5) {
            h5WebSessionManage(request, response, commonReqParam);
            return true;
        }

        if (!checkAuthParam(request, response, auth, commonReqParam)) {
            return false;
        }

        return true;
    }

    /**
     * 封装公共请求参数
     *
     * @param request 请求对象
     * @return 公共请求参数
     */
    private CommonReqParam packagingCommonReqParam(HttpServletRequest request) {
        String appKey = request.getParameter("appKey");
        String accessToken = request.getParameter("accessToken");
        String timestamp = request.getParameter("timestamp");
        String source = request.getParameter("source");
        CommonReqParam commonReqParam = new CommonReqParam();
        commonReqParam.setAppKey(StringUtil.isEmpty(appKey) ? null : appKey);
        commonReqParam.setAccessToken(StringUtil.isEmpty(accessToken) ? null : accessToken);
        commonReqParam.setTimestamp(StringUtil.isDigit(timestamp) ? Long.parseLong(timestamp) : null);
        commonReqParam.setSource(StringUtil.isInt(source) ? Integer.parseInt(source) : null);
        return commonReqParam;
    }

    /**
     * 检查授权方法
     *
     * @param request  请求对象
     * @param response 响应对象
     * @param auth     授权信息
     * @return 检查是否通过
     */
    private boolean checkAuthMethod(HttpServletRequest request, HttpServletResponse response, CheckAuth auth) {
        if (auth == null || auth.method().length == 0) {
            return true;
        }

        String requestMethod = request.getMethod();

        for (AuthMethod authMethod : auth.method()) {
            if (authMethod.toString().equalsIgnoreCase(requestMethod)) {
                return true;
            }
        }

        writeResponse(response, JsonResult.fail(GlobalResultStatus.REQUEST_METHOD_ERROR));
        return false;
    }

    /**
     * 检查授权Scheme
     *
     * @param request  请求对象
     * @param response 响应对象
     * @param auth     授权信息
     * @return 检查是否通过
     */
    private boolean checkAuthScheme(HttpServletRequest request, HttpServletResponse response, CheckAuth auth) {
        if (auth == null || auth.scheme().length == 0) {
            return true;
        }

        String requestUrl = getRequestUrl(request);
        URI uri = null;
        try {
            uri = new URI(requestUrl);
        } catch (URISyntaxException e) {
            writeResponse(response, JsonResult.fail(GlobalResultStatus.REQUEST_URL_ERROR));
            return false;
        }

        String requestScheme = uri.getScheme();

        for (AuthScheme authScheme : auth.scheme()) {
            if (authScheme.toString().equalsIgnoreCase(requestScheme)) {
                return true;
            }
        }

        writeResponse(response, JsonResult.fail(GlobalResultStatus.REQUEST_SCHEME_ERROR));
        return false;
    }


    /**
     * 检查公共请求参数
     *
     * @param request        请求对象
     * @param response       响应对象
     * @param commonReqParam 公共请求参数
     * @return 检查是否通过
     */
    private boolean checkCommonReqParamEmpty(HttpServletRequest request, HttpServletResponse response, CommonReqParam commonReqParam) {

        //检查appKey参数是否为空
        if (StringUtil.isEmpty(commonReqParam.getAppKey())) {
            writeResponse(response, JsonResult.fail(GlobalResultStatus.PARAM_APPKEY_MISSING));
            return false;
        }

        //检查accessToken参数是否为空
        if (StringUtil.isEmpty(commonReqParam.getAccessToken())) {
            writeResponse(response, JsonResult.fail(GlobalResultStatus.PARAM_ACCESSTOKEN_MISSING));
            return false;
        }

        //检查timestamp参数是否为空
        if (commonReqParam.getTimestamp() == null) {
            writeResponse(response, JsonResult.fail(GlobalResultStatus.PARAM_TIMESTAMP_MISSING));
            return false;
        }

        if (Math.abs(System.currentTimeMillis() - commonReqParam.getTimestamp()) > 10 * 60 * 1000) {
            writeResponse(response, JsonResult.fail(GlobalResultStatus.TIMESTAMP_EXPIRED, System.currentTimeMillis()));
            return false;
        }

        //检查source参数是否为空
        if (commonReqParam.getSource() == null) {
            writeResponse(response, JsonResult.fail(GlobalResultStatus.PARAM_SOURCE_MISSING));
            return false;
        }

        return true;
    }

    private void h5WebSessionManage(HttpServletRequest request, HttpServletResponse response, CommonReqParam commonReqParam) {
        String url = request.getRequestURI();
        HttpSession session = request.getSession();
        log.info(url);
        Account account = (Account) session.getAttribute(BaseConstant.ADMIN_SESSION);
        log.info("-------zblog-------read：session_id:" + request.getSession().getId());
        log.info("Admin:" + account);
        if (!url.contains("session")) {
            if (account == null) {
                log.debug("-------zblog-------admin is null:" + request.getSession().getId());
                writeResponse(response, JsonResult.fail(GlobalResultStatus.USER_LOGIN_SESSION_TIME_OUT));
                return;
            }
        }
    }

    /**
     * 检查授权参数
     *
     * @param request  请求对象
     * @param response 响应对象
     * @param auth     授权信息
     * @return 检查是否通过
     */
    private boolean checkAuthParam(HttpServletRequest request, HttpServletResponse response, CheckAuth auth, CommonReqParam commonReqParam) {

        //没有授权注解，则通过，其他参数不做验证。
        if (auth == null) {
            return true;
        }

        String appSecret = appInfoService.getByAppKey(commonReqParam.getAppKey()).getAppSecret();

        //检查配置的参数
        for (AuthParam authParam : auth.param()) {
            switch (authParam) {
                case ACCESS_TOKEN://检查访问凭证
                    if (!checkAccessToken(commonReqParam.getAccessToken(), commonReqParam.getAppKey(), response)) {
                        return false;
                    }
                    break;
                case REFRESH_TOKEN://检查刷新凭证
                    String refreshToken = request.getParameter("refreshToken");
                    if (StringUtil.isEmpty(refreshToken)) {
                        writeResponse(response, JsonResult.fail(GlobalResultStatus.AUTH_MISSING));
                        return false;
                    }
                    if (tokenService.getUserIdByRefreshToken(refreshToken, commonReqParam.getAppKey()) == null) {
                        writeResponse(response, JsonResult.fail(GlobalResultStatus.REFRESH_TOKEN_ERROR));
                        return false;
                    }
                    break;
                case SIGN://检查签名
                    String sign = request.getParameter("sign");
                    if (StringUtil.isEmpty(sign)) {
                        writeResponse(response, JsonResult.fail(GlobalResultStatus.AUTH_MISSING));
                        return false;
                    }
                    String checkedSign = getSign(request, appSecret);
                    if (!checkedSign.equals(sign)) {
                        writeResponse(response, JsonResult.fail(GlobalResultStatus.SIGNATURE_ERROR));
                        return false;
                    }
                    break;
                default:
            }
        }

        return true;
    }

    /**
     * 检查accessToken
     *
     * @param accessToken
     * @param appKey
     * @param response
     * @return
     */
    private boolean checkAccessToken(String accessToken, String appKey, HttpServletResponse response) {

        if (tokenService.getAuthByAccessToken(accessToken, appKey) == null) {
            Auth expiresAuth = tokenService.getAuthByExpiresToken(accessToken, appKey);
            if (expiresAuth == null) {
                writeResponse(response, JsonResult.fail(GlobalResultStatus.ACCESS_TOKEN_ERROR));
                return false;
            } else {
                ResultStatus resultStatus = new ResultStatus(100021006, "你的账号于#" + DateUtil.format(expiresAuth.getLoginTime(), "HH:mm") + "#在其它地方登录，如果这不是你的操作，你的密码可能已泄露。请重新登录并重置你的密码。");
                writeResponse(response, resultStatus);
                return false;
            }
        }
        return true;
    }

    /**
     * 根据请求信息生成签名
     *
     * @param request   请求对象
     * @param appSecret APP密钥
     * @return 签名值
     */
    private String getSign(HttpServletRequest request, String appSecret) {
        Map<String, String> params = new TreeMap<String, String>();
        for (Enumeration<String> paramNames = request.getParameterNames(); paramNames.hasMoreElements(); ) {
            String paramName = paramNames.nextElement();
            params.put(paramName, request.getParameter(paramName));
        }
        params.remove("sign");

        StringBuilder buf = new StringBuilder();
        buf.append(request.getMethod()).append(getRequestUrl(request));
        for (Map.Entry<String, String> param : params.entrySet()) {
            buf.append(param.getKey()).append("=").append(param.getValue()).append("&");
        }
        buf.append(appSecret);

        String md5 = DigestUtils.md5Hex(buf.toString());
        return md5;
    }

    /**
     * 获取客户端请求地址
     *
     * @param request 请求对象
     * @return 客户端请求地址
     */
    private String getRequestUrl(HttpServletRequest request) {
        //从反向代理服务器获取客户端请求地址，需要反向代理服务器配置此参数
        String requestUrl = request.getHeader("X-Request-URI");
        if (requestUrl == null) {
            //从Servlet服务器获取客户端请求地址
            requestUrl = request.getRequestURL().toString();
        }
        return requestUrl;
    }

    /**
     * 写响应数据
     *
     * @param response 响应对象
     * @param result   响应结果
     */
    private void writeResponse(HttpServletResponse response, Object result) {
        response.setContentType("application/json;charset=UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        try {
            response.getWriter().write(mapper.writeValueAsString(result));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
