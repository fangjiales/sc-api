package com.fjl.blob.handler;

import com.alibaba.fastjson.JSON;
import com.fjl.blob.enums.ResultCodeEnums;
import com.fjl.blob.result.Result;
import com.fjl.blob.utils.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String string;
        if (authException.getMessage().equals(ResultCodeEnums.USERNAME_PASSWORD_ERROR.getMessage())) {
            string = JSON.toJSONString(Result.build(ResultCodeEnums.USERNAME_PASSWORD_ERROR));
        } else if (authException.getMessage().equals(ResultCodeEnums.USER_DISABLE.getMessage())) {
            string = JSON.toJSONString(Result.build(ResultCodeEnums.USER_DISABLE));
        } else {
            string = JSON.toJSONString(Result.build(ResultCodeEnums.AUTHENTICATION_FAIL));
        }
        WebUtil.renderString(response, string);
    }
}
