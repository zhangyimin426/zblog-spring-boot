package com.zym.api.blog.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zym.common.base.constant.BaseConstant;
import com.zym.common.base.model.Account;
import com.zym.common.base.statuscode.GlobalResultStatus;
import com.zym.common.base.utils.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * session管理（使用注解标注过滤器）
 * @WebFilter将一个实现了javax.servlet.Filter接口的类定义为过滤器 属性filterName声明过滤器的名称, 可选
 * 属性urlPatterns指定要过滤的URL模式,也可使用属性value来声明.(指定要过滤的URL模式是必选属性)
 * @author Gavin
 * @date 2016-09-26
 */

@WebFilter(filterName = "sessionFilter", urlPatterns = "/*")
public class SessionFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(SessionFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化session过滤器");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String url = request.getRequestURI();
        HttpSession session = request.getSession();
        log.info(url);
        Account account = (Account) session.getAttribute(BaseConstant.ADMIN_SESSION);
        System.out.println("-------zblog-------read：session_id:"+request.getSession().getId());
        System.out.println("Admin:" + account);
        if (!url.contains("session")) {
            if (account == null) {
                log.debug("-------zblog-------admin is null:"+request.getSession().getId());
                writeResponse(response, JsonResult.fail(GlobalResultStatus.USER_LOGIN_SESSION_TIME_OUT));
                return;
            }
        }
        chain.doFilter(request, response);
    }

    private void writeResponse(HttpServletResponse response, Object result) {
        response.setContentType("application/json;charset=UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        try {
            response.getWriter().write(mapper.writeValueAsString(result));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void destroy() {

    }

}
