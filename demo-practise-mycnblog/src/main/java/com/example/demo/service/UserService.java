package com.example.demo.service;

import com.example.demo.entity.UserInfo;
import com.example.demo.mapper.UserMapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public int add(UserInfo userInfo){
        return userMapper.add(userInfo);
    }

    public UserInfo getUserByName(String username){
        return userMapper.getUserByName(username);
    }

    public UserInfo getUserById(Integer id){
        return userMapper.getUserById(id);
    }
}
