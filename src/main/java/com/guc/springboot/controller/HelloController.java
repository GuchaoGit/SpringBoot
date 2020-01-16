package com.guc.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author guc
 * @Date 2020/1/16 16:28
 * @Description HelloController
 */
//注意这里已经不是@RestController了 和上个试验不同，因为需要返回视图，所以不能使用@ResponseBody
@Controller
public class HelloController {
    @RequestMapping("welcome")
    public String welcome(){
        return "hello";
    }
}
