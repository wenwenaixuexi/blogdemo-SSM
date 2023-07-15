package com.example.demo.controller;

import com.example.demo.common.AjaxResult;
import com.example.demo.common.AppVariable;
import com.example.demo.common.PasswordUtils;
import com.example.demo.common.UserSessionUtils;
import com.example.demo.entity.UserInfo;
import com.example.demo.entity.vo.UserInfoVO;
import com.example.demo.service.ArticleService;
import com.example.demo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/reg")
    public AjaxResult add(UserInfo userInfo){
        //1.非空校验
        if(userInfo == null || !StringUtils.hasLength(userInfo.getUsername()) ||
        !StringUtils.hasLength(userInfo.getPassword())){
            return AjaxResult.fail(-1,"非法请求");
        }
        //密码加盐处理
        userInfo.setPassword(PasswordUtils.encrypt(userInfo.getPassword()));
        //2.插入用户
        return AjaxResult.success(userService.add(userInfo));


    }
    @RequestMapping("/login")
    public AjaxResult login(HttpServletRequest request,String username, String password){
        //1.非空校验
        if(!StringUtils.hasLength(username) ||
        !StringUtils.hasLength(password)){
            return AjaxResult.fail(-1,"非法参数");

        }
        //2.查询数据库
        UserInfo userInfo = userService.getUserByName(username);
        if(userInfo != null && userInfo.getId()>0){
            //有效用户名
            //两个密码是否相同
//            if(password.equals(userInfo.getPassword())){
                if(PasswordUtils.check(password,userInfo.getPassword())){
                //将用户存储到session
                HttpSession session = request.getSession();
                session.setAttribute(AppVariable.USER_SESSION_KEY,userInfo);

                userInfo.setPassword("");//返回给前端之前隐藏敏感信息
                return AjaxResult.success(userInfo);
            }

        }
        return AjaxResult.success(0,null);
    }

    @RequestMapping("/showinfo")
    public AjaxResult showInfo(HttpServletRequest request){
        UserInfoVO userInfoVO = new UserInfoVO();
        //1.得到当前登陆用户（从session中获取）
        UserInfo userInfo = UserSessionUtils.getSessUser(request);
        if(userInfo == null){
            return AjaxResult.fail(-1,"非法请求");
        }
        //Spring提供得深克隆方法
        BeanUtils.copyProperties(userInfo,userInfoVO);
        userInfoVO.setArtCount(articleService.getArtCountByUid(userInfo.getId()));
        System.out.println(userInfoVO.getArtCount());
        //2.得到用户发表文章得总数
        return AjaxResult.success(userInfoVO);
    }
    //注销
    @RequestMapping("/logout")
    public AjaxResult logout(HttpSession session){
        session.removeAttribute(AppVariable.USER_SESSION_KEY);
        return AjaxResult.success(1);
    }

    //查询用户信息
    @RequestMapping("/getuserbyid")
    public AjaxResult getUserById(Integer id){
        if(id == null || id <= 0){
            return AjaxResult.fail(-1,"参数非法");
        }
        UserInfo userInfo = userService.getUserById(id);
        if(userInfo == null || userInfo.getId() <= 0){
            return AjaxResult.fail(-1,"参数非法");
        }

        //取出userinf中得敏感数据
        userInfo.setPassword("");
        //查询当前用户发表得文章数
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(userInfo,userInfoVO);
        userInfoVO.setArtCount(articleService.getArtCountByUid(id));
        return AjaxResult.success(userInfoVO);

    }

}
