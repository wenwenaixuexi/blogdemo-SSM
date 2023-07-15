package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ArticleInfo implements Serializable {

    private Integer id;
    private String title;
    private String content;
    private LocalDateTime createtime;
    private LocalDateTime updatetime;
    private Integer uid;
    private Integer rcount;
    private Integer state;

}
