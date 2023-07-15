package com.example.demo.mapper;

import com.example.demo.entity.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;
    @Test
    void add() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("afd");
        userInfo.setPassword("1212");
        userMapper.add(userInfo);

    }

    @Test
    void getUserByName() {
        UserInfo userInfo = userMapper.getUserByName("121");
        System.out.println(userInfo);
    }

    @Test
    void getUserByUid() {
        UserInfo userInfo = userMapper.getUserById(4);
        System.out.println(userInfo);
    }
}