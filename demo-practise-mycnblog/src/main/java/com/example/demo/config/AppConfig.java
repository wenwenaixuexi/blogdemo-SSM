package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/editor.md/**")
                .excludePathPatterns("/img/**")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/login.html")
                .excludePathPatterns("/reg.html")
                .excludePathPatterns("/blog_list.html")
                .excludePathPatterns("/art/detail")
                .excludePathPatterns("/blog_content.html")
                .excludePathPatterns("/art/uprcount")
                .excludePathPatterns("/art/del")
                .excludePathPatterns("/user/getuserbyid")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/reg")
                .excludePathPatterns("/art/mylist")
                .excludePathPatterns("/art/add")
                .excludePathPatterns("/art/update")
                .excludePathPatterns("/set")

                .excludePathPatterns("/annget")
                .excludePathPatterns("/put")
                .excludePathPatterns("/del")


                .excludePathPatterns("/art/listbypage");







    }
}
