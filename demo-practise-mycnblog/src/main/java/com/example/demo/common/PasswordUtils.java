package com.example.demo.common;
//为什么要进行加盐加密
//1.明文-》不行的，会泄露隐私
//2.传统的MD5-》又规律可循【虽然不可能，但是可以i被暴力破解】
//彩虹表（记录了几乎所有字符串的MD5对照表）
//3.加盐加密：安全   盐值随机-》没有规律可言
//明文密码12345 -》第一次调用xxx-》第二次调用xxy



//加盐实现思路：
//每次调用方法的时候产生盐值（唯一）+密码 = 最终密码


//解密思路：
//需要两个密码：
//1.需要验证的密码（用户输入的密码）
//2.最终加密的密码（存在数据库中的密码）
//核心思想：得到盐值  -》存到最终密码的某个位置
//最终密码格式：盐值{32位}$最终的密码{32}位

//验证密码伪代码
//已有：用户输入的明文密码、此用户再数据库存储的最终密码=【盐值$加密后的密码】

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

//1.从最终密码中得到盐值
//2.将用户输入的明文密码+盐值进行加密才做=加密后的密码
//3.使用盐值+分隔符+加密后的密码生成数据库存储的密码
//4.对比生成的最终密码和数据库最终的密码是否相等
//如果相等，那么用户名和密码就是对的
//反之就是密码输入错误
public class PasswordUtils {
    //1.加盐并生成密码  明文密码  -》保存到数据库中的密码
    public static String encrypt(String password){
        //a.产生盐值（32位）
        String salt = UUID.randomUUID().toString().replace("-","");
        //2.生成加盐之后的密码
        String saltPassword = DigestUtils.md5DigestAsHex((salt+password).getBytes());
        //3.生成最终密码（保存到数据库中的密码）【约定格式：32位盐值+$+32位加盐之后的密码】
        String finalPassword = salt + "$" + saltPassword;
        return finalPassword;
    }
    //2.生成加盐的密码（方法1的重载）  明文 固定的盐值-》最终密码
    public static String encrypt(String password,String salt){
        //1.生成一个加盐之后的密码
        String saltPassword = DigestUtils.md5DigestAsHex((salt+password).getBytes());
        //2.生成最终的密码
        String finalPassword = salt + "$" + saltPassword;
        return finalPassword;
    }



    //3.验证密码  inputPassword:用户输入的明文密码  finalPassword:数据库保存的最终密码
    public static boolean check(String inputPassword,String finalPassword){
        if(StringUtils.hasLength(inputPassword) && StringUtils.hasLength(finalPassword)
        && finalPassword.length() == 65){
            //1.得到盐值
            String salt = finalPassword.split("\\$")[0];
            //2.使用之前加密的步骤，将明文密码和已经得到的盐值进行加密，生成最终的密码
            String confirmPassword = PasswordUtils.encrypt(inputPassword,salt);

            //3.对比两个密码是否相同
            return confirmPassword.equals(finalPassword);

        }
        return false;

    }




    public static void main(String[] args) {
        String password = "12345";
//        System.out.println("第一加密："+PasswordUtils.encrypt(password));
//        System.out.println("第二加密："+PasswordUtils.encrypt(password));
//        System.out.println("第三加密："+PasswordUtils.encrypt(password));

        String finalPassword = PasswordUtils.encrypt(password);
        System.out.println("加密："+finalPassword);

        //对比
        String inputPassword = "12345";
        System.out.println("对比"+inputPassword+"是否等于"+password + "-》"+PasswordUtils.check(inputPassword,finalPassword));

        String inputPassword2 = "123456";
        System.out.println("对比"+inputPassword2+"是否等于"+password + "-》"+PasswordUtils.check(inputPassword2,finalPassword));



    }

}
