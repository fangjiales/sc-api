package com.fjl.blob.handler;

import com.alibaba.fastjson.JSON;
import com.fjl.blob.enums.ResultCodeEnums;
import com.fjl.blob.result.Result;
import com.fjl.blob.utils.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String string = JSON.toJSONString(Result.build(ResultCodeEnums.LACK_PERMISSION));
        WebUtil.renderString(response, string);
    }
}
