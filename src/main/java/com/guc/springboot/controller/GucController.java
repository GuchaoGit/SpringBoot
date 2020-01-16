package com.guc.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author guc
 * @Date 2020/1/16 11:53
 * @Description 第一个Controller
 */
@RestController
//RestController相当于同时使用@Controller和@ResponseBody注解
//加载classpath目录下的guc.properties文件
@PropertySource(value = "classpath:guc.properties" ,encoding = "UTF-8")
public class GucController {

    //使用@Value注解注入自定义属性值
    @Value("${guc.springboot.test}")
    private String hello;

    @RequestMapping(value = "hello")
    public String hello(){
        return hello;
    }

}
