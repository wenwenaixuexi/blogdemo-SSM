package com.example.demo.common;

import com.example.demo.entity.UserInfo;
import org.apache.catalina.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//当前登陆用户相关得操作
public class UserSessionUtils {
    public static UserInfo getSessUser(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null &&
        session.getAttribute(AppVariable.USER_SESSION_KEY) != null){
            //说明用户已经正常登陆
            return (UserInfo)session.getAttribute(AppVariable.USER_SESSION_KEY);
        }
        return null;
    }
}
