package com.example.demo.controller;

import com.example.demo.common.AjaxResult;
import com.example.demo.common.UserSessionUtils;
import com.example.demo.entity.ArticleInfo;
import com.example.demo.entity.UserInfo;
import com.example.demo.entity.vo.UserInfoVO;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/art")
public class ArtcleController {
    //小数点向上取整

    public static void main(String[] args) {
        int totalCount = 4;
        int psize = 2;
        double pcount = totalCount/(psize*1.0);
        System.out.println(Math.ceil(pcount));
    }


    @Autowired
    private ArticleService articleService;

    @RequestMapping("/mylist")
    public AjaxResult getMyList(HttpServletRequest request){
        UserInfo userInfo = UserSessionUtils.getSessUser(request);
        if(userInfo == null){
            return AjaxResult.fail(-1,"非法请求");
        }
        List<ArticleInfo> list = articleService.getMyList(userInfo.getId());
        return AjaxResult.success(list);
    }
    @RequestMapping("/del")
    public AjaxResult del(HttpServletRequest request,Integer id){
        if(id == null || id < 0){
            //参数有误
            return AjaxResult.fail(-1,"参数异常");

        }
        UserInfo user = UserSessionUtils.getSessUser(request);
        if(user == null){
            return AjaxResult.fail(-2,"用户未登录");
        }
        return AjaxResult.success(articleService.del(id,user.getId()));
    }

    @RequestMapping("/detail")
    public AjaxResult getDetail(Integer id){
        if(id == null || id < 0){
            return AjaxResult.fail(-1,"请求参数非法");
        }
        return AjaxResult.success(articleService.getDetail(id));
    }

    @RequestMapping("/uprcount")
    public AjaxResult updateRCount(Integer id){
        if(id == null || id < 0){
            return AjaxResult.fail(-1,"参数非法");
        }
        return AjaxResult.success(articleService.incrRcount(id));
    }

    @RequestMapping("/add")
    public AjaxResult add(HttpServletRequest request,ArticleInfo articleInfo){
        //1.非空校验
        if(articleInfo == null || !StringUtils.hasLength(articleInfo.getTitle()
                )|| !StringUtils.hasLength(articleInfo.getContent())){
            return AjaxResult.fail(-1,"非法参数");
        }
        //2.数据库添加操作
        //a。得到当前登陆用户得uid
        UserInfo userInfo = UserSessionUtils.getSessUser(request);
        if(userInfo == null || userInfo.getId() <= 0){
            //无效得登陆用户
            return AjaxResult.fail(-2,"无效得登陆用户");
        }
        articleInfo.setUid(userInfo.getId());
        //b.添加数据库并发挥结果
        return AjaxResult.success(articleService.addArticle(articleInfo));
    }

    @RequestMapping("/update")
    public AjaxResult update(HttpServletRequest request,ArticleInfo articleInfo){
        //非空校验
        //得到当前登陆得用户id
        //无效用户
        if(articleInfo == null || !StringUtils.hasLength(articleInfo.getTitle())
                || !StringUtils.hasLength(articleInfo.getContent())||
              articleInfo.getId() == null){
            AjaxResult.fail(-1,"参数非法");
        }

        UserInfo userinfo =UserSessionUtils.getSessUser(request);
        if(userinfo == null || userinfo.getId() == null){
            AjaxResult.fail(-2,"无效用户");
        }
        articleInfo.setUid(userinfo.getId());
        articleInfo.setTitle(articleInfo.getTitle());
        articleInfo.setContent(articleInfo.getContent());

        articleInfo.setUpdatetime(LocalDateTime.now());
        return AjaxResult.success(articleService.update(articleInfo));

    }

    /**
     *
     *查询列表根据分页
     * pindex 当前页码（从1开始）
     * psize 每页显示条数
     * */

    @RequestMapping("/listbypage")
    public AjaxResult getListByPage(Integer pindex,Integer psize){
        //1.参数矫正
        if(pindex == null || pindex <= 1){
            pindex = 1;
        }
        if(psize == null || psize <= 1){
            psize = 2;
        }
        //分页公式的值 = （当前页码-1）*每页条数
        int offset = (pindex-1)*psize;
        //文章列表数据
        List<ArticleInfo> list = articleService.getListByPage(psize,offset);
        //当前列表总共有多少页
        //a.总共有多少条数据
        int count = articleService.getCount();
        if(count == 0 || count < 0){
            AjaxResult.fail(-1,"参数有误");
        }
        //b.总条数/psize(每页显示条数)
        //c.使用进一法得到总页数
        int pcount = (int) Math.ceil(count / (psize*1.0));
        HashMap<String,Object> res = new HashMap<>();
        res.put("list", list);
        res.put("pcount",pcount);
        return AjaxResult.success(res);

    }




}
