package com.example.demo.config;

import com.example.demo.common.AjaxResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

//实现统一数据返回的保底类
//说明：在返回数据之前，检测数据的类型是否为统一的对象，如果不是，封装成统一的对象
@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice {
    @Autowired
    private ObjectMapper objectMapper;
    //开关，如果是true，才会调用breforeBodyWrite
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    //对数据格式进行校验和封装
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(body instanceof AjaxResult)
            return body;
        if(body instanceof String){
            try {
                return objectMapper.writeValueAsString(AjaxResult.success(body));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return AjaxResult.success(body);
    }
}
