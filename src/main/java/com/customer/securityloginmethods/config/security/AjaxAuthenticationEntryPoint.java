package com.customer.securityloginmethods.config.security;

import com.alibaba.fastjson.JSON;import com.customer.securityloginmethods.config.common.ResponseBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 未登陆不能访问url的处理器
 */
@Component
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseBean responseBody = new ResponseBean();

        responseBody.setCode(400);
                responseBody.setMsg("没有权限，请先登陆");
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }
}