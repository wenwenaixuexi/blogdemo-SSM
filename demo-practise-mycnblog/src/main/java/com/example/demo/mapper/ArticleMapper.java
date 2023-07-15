package com.example.demo.mapper;

import com.example.demo.entity.ArticleInfo;
import com.example.demo.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper {
    int getArtCountByUid(@Param("uid") Integer uid);

    List<ArticleInfo> getMyArtList(@Param("uid") Integer uid);

    int del(@Param("id")Integer id,Integer uid);

    //文章详情页得实现思路
    //1.从url中得到文章id
    //2.从后端查询当前文章得详情信息（以及uid）
    //3.根据上一步查询得uid查询用户得信息
    //4.请求后端接口实现阅读量+1
    ArticleInfo getDetail(@Param("id") Integer id);


     int incrRCount(@Param("id")Integer id);

     int addArticle(ArticleInfo articleInfo);


     //博客修改页面实现：
    //1.得到文章id
    //2.去后端查询文章得详情信息并设置到页面上
    //3.进行文章修改操作（调用后台）

     int update(ArticleInfo articleInfo);


     List<ArticleInfo> getListByPage(@Param("psize") Integer psize,
                                     @Param("offset") Integer offset);


     int getCount();



}
