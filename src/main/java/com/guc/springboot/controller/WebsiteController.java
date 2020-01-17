package com.guc.springboot.controller;

import com.guc.springboot.entity.Log;
import com.guc.springboot.entity.Website;
import com.guc.springboot.service.LogService;
import com.guc.springboot.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author guc
 * @Date 2020/1/17 10:57
 * @Description 网站Controller
 */
@Controller
public class WebsiteController {
    @Autowired
    private WebsiteService websiteService;

    @Autowired
    private LogService logService;

    @RequestMapping("websites")
    @ResponseBody
    public String websites(){
        List<Website> websites = websiteService.queryAll();
        StringBuilder stringBuilder = new StringBuilder();
        if (websites!=null){
            for (Website a:websites){
                stringBuilder.append(a.toString());
            }
        }
        return stringBuilder.toString();
    }

    @RequestMapping("website/{id}")
    @ResponseBody
    public String websiteById(@PathVariable int id){
        return websiteService.queryById(id).toString();
    }
    @RequestMapping("siteLog/{id}")
    @ResponseBody
    public String logBySiteId(@PathVariable int id){
        List<Log> logs = logService.queryLogBySiteId(id);
        StringBuilder stringBuilder = new StringBuilder();
        if (logs!=null){
            for (Log a:logs){
                stringBuilder.append(a.toString());
            }
        }
        return stringBuilder.toString();
    }
}
