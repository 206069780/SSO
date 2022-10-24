package com.blog.sso.hanld;

import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomizeAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        String result = "";
        if (e instanceof AccountExpiredException) {
            //账号过期
            result = e.getMessage();
        } else if (e instanceof BadCredentialsException) {
            //密码错误
            result = e.getMessage();
        } else if (e instanceof CredentialsExpiredException) {
            //密码过期
            result = e.getMessage();
        } else if (e instanceof DisabledException) {
            //账号不可用
            result = e.getMessage();
        } else if (e instanceof LockedException) {
            //账号锁定
            result = e.getMessage();
        } else if (e instanceof InternalAuthenticationServiceException) {
            //用户不存在
            result = e.getMessage();
        } else {
            //其他错误
            result = e.getMessage();
        }
        //处理编码方式，防止中文乱码的情况
        httpServletResponse.setContentType("text/json;charset=utf-8");
        // 把Json数据放入到HttpServletResponse中返回给前台
        httpServletResponse.getWriter().write(result);
    }
}
