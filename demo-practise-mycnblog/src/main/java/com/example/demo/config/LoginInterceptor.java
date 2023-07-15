package com.example.demo.config;

import com.example.demo.common.AppVariable;
import com.example.demo.entity.UserInfo;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
   //true 用户已登陆
    //false 用户未登录

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute(AppVariable.USER_SESSION_KEY )!= null){
            //用户已经登陆
            System.out.println("当前用户："+((UserInfo)session.getAttribute(AppVariable.USER_SESSION_KEY)).getUsername());

            return true;
        }
        //跳转到登陆页
        response.sendRedirect("/login.html");
        return false;
    }
}
