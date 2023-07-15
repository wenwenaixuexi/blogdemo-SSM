package com.example.demo.common;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;
import java.nio.charset.StandardCharsets;
//使用Spring Security进行加盐和验证实现步骤：
//1.引入Spring Security框架
//<dependency>
//<groupId>org.springframework.boot</groupId>
//<artifactId>spring-boot-starter-security</artifactId>
//</dependency>

//2.排除Spring Security自动加载
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
//3.调用Spring Security加盐和验证


public class liangdian {
    public static void jiami(){
        BCryptPasswordEncoder passwordEncoder =
                new BCryptPasswordEncoder();
        String password = "123456";
        String finalPassword = passwordEncoder.encode(password);
        System.out.println("第一次加密"+finalPassword);
        System.out.println("第二次加密"+passwordEncoder.encode(password));

        //验证
        String inputPassword = "12345";
        System.out.println("错误密码对比结果："+passwordEncoder.matches(inputPassword,finalPassword));
        String inputPassword2 = "123456";
        System.out.println("正确密码对比结果："+passwordEncoder.matches(inputPassword2,finalPassword));
    }


    public static void main(String[] args) {
        String password = "123456";
        String mdString = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        System.out.println(mdString);
    }
}
