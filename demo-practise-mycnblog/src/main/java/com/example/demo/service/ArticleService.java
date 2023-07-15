package com.example.demo.service;

import com.example.demo.entity.ArticleInfo;
import com.example.demo.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleService {
    @Resource
    private ArticleMapper articleMapper;
    public int getArtCountByUid(Integer uid){
        return articleMapper.getArtCountByUid(uid);
    }

    public List<ArticleInfo> getMyList(Integer uid){
        return articleMapper.getMyArtList(uid);
    }

    public int del(Integer id,Integer uid){
        return articleMapper.del(id,uid);
    }

    public ArticleInfo getDetail(Integer id){
        return articleMapper.getDetail(id);
    }

    public int incrRcount(Integer id){
        return articleMapper.incrRCount(id);
    }

    public int addArticle(ArticleInfo articleInfo){
        return articleMapper.addArticle(articleInfo);
    }

    public int update(ArticleInfo articleInfo){
        return articleMapper.update(articleInfo);
    }

    public List<ArticleInfo> getListByPage(Integer psize,Integer offsize){
        return articleMapper.getListByPage(psize,offsize);
    }

    public int getCount(){
        return articleMapper.getCount();
    }


}
