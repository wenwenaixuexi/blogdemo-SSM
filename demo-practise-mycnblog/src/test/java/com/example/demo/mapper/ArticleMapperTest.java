package com.example.demo.mapper;

import com.example.demo.entity.ArticleInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleMapperTest {

    @Autowired
    private ArticleMapper articleMapper;
    @Test
    void getArtCountByUid() {
        int result = articleMapper.getArtCountByUid(1);
        System.out.println(result);
    }

    @Test
    void getMyArtList() {
        List<ArticleInfo> articleInfo = articleMapper.getMyArtList(6);
        System.out.println(articleInfo);
    }

    @Test
    void getDetail() {
        ArticleInfo articleInfo = articleMapper.getDetail(4);
        System.out.println(articleInfo);

    }

    @Test
    void getUserByUid() {

    }

    @Test
    void addArticle() {
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setUid(22);
        articleInfo.setTitle("标题四");
        articleInfo.setContent("内容四");
        articleMapper.addArticle(articleInfo);
    }

    @Test
    void getCount() {
        System.out.println(articleMapper.getCount());
    }

    @Test
    void getListByPage() {
        List<ArticleInfo> list = articleMapper.getListByPage(2,0);
        System.out.println(list);

    }


}