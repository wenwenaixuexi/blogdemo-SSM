package com.example.demo.entity.vo;

import com.example.demo.entity.UserInfo;
import lombok.Data;

@Data
public class UserInfoVO extends UserInfo {
    private Integer artCount; //此人发表得文章总数
}
