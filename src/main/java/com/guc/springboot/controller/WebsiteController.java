package com.guc.springboot.controller;

import com.guc.springboot.account.entity.Account;
import com.guc.springboot.account.service.AccountService;
import com.guc.springboot.entity.Log;
import com.guc.springboot.entity.Website;
import com.guc.springboot.service.LogService;
import com.guc.springboot.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @Autowired
    private AccountService accountService;

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

    @RequestMapping("accounts")
    @ResponseBody
    public String accounts(){
        List<Account> accounts = accountService.queryAll();
        StringBuilder stringBuilder = new StringBuilder();
        if (accounts!=null){
            for (Account a:accounts){
                stringBuilder.append(a.toString());
            }
        }
        return stringBuilder.toString();
    }

    @RequestMapping(value = "account/transfer",method = RequestMethod.POST)
    @ResponseBody
    public String transfer(@RequestParam Map<String,Object> params){
        String inner,outer;
        int money;
        if (!params.containsKey("inner")){
            return "缺少参数 inner";
        }
        inner = (String) params.get("inner");
        if (!params.containsKey("outer")){
            return "缺少参数 outer";
        }
        outer = (String) params.get("outer");
        if (!params.containsKey("money")){
            return "Map - "  + params +"  缺少参数 money";
        }
        money = Integer.valueOf((String) params.get("money"));
        int res = accountService.transfer(outer,inner,money);
        if (res == 0){
            return outer +"已成功向" + inner +" 汇款:" +money+"元";
        }else {
            return "汇款失败";
        }
    }
}
