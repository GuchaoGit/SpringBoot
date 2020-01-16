package com.guc.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author guc
 * @Date 2020/1/16 11:53
 * @Description 第一个Controller
 */
@RestController
//RestController相当于同时使用@Controller和@ResponseBody注解
public class GucController {

    @RequestMapping("hello")
    public String hello(){
        return "Hello Guc";
    }

}
