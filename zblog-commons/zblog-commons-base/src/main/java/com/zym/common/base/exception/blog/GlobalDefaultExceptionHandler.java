package com.zym.common.base.exception.blog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zym.common.base.statuscode.ResultStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Gavin
 * @date 2016-09-27
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Object defaultExceptionHandler(Exception ex, HttpServletRequest request) {
        ResultStatus resultStatus = new ResultStatus(500, ex.getMessage());
        return resultStatus;
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
