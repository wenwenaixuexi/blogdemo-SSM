package com.example.demo.mapper;

import com.example.demo.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    //注册
    int add(UserInfo userInfo);
    //根据用户名查询userinfo对象
     UserInfo getUserByName(@Param("username") String username);
    //根据用户id查询userinfo对象
     UserInfo getUserById(Integer id);

}
