package com.zym.business.blog.interceptor;


import com.zym.business.blog.service.BlogServiceImpl;
import com.zym.common.base.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 服务调用拦截
 *
 * @author Gavin
 * @date 2016-09-29
 */
public class ServiceInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(ServiceInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HessianServiceExporter exporter = null;
        if (handler instanceof HessianServiceExporter) {
            exporter = (HessianServiceExporter) handler;
        }

        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;
        String remoteAddr = getIpAddr(request);
        log.info("调用者【" + remoteAddr + "】，调用的服务【" + exporter.getService() + "】的时间：" + executeTime + "ms");
    }

    /**
     * 获取客户端Ip地址
     *
     * @return
     */
    protected String getIpAddr(HttpServletRequest request) {
        // 通过请求头获取ip地址
        String ip = request.getHeader("x-forwarded-for");
        // 判断ip地址是否是代理地址
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        // 判断ip地址是否是代理地址
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        // 判断ip地址是否是代理地址
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
