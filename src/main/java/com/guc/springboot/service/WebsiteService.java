package com.guc.springboot.service;

import com.guc.springboot.entity.Log;
import com.guc.springboot.entity.Website;
import com.guc.springboot.repository.LogRepository;
import com.guc.springboot.repository.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author guc
 * @Date 2020/1/13 14:13
 * @Description 提供数据  作为 service 层
 */
@Service
public class WebsiteService {
    @Autowired
    private WebsiteRepository websiteRepository;

    @Autowired
    private LogRepository logRepository;
    /**
     * 查询所有
     * @return 网址列表
     */
    @Transactional(rollbackFor = Exception.class) //添加事务 异常回滚
    public List<Website> queryAll(){
        Website website;
        List<Website> websites = new ArrayList<>();
        Iterable<Website> iterable = websiteRepository.findAll();
        for (Iterator iterator = iterable.iterator();iterator.hasNext();){
            website = (Website) iterator.next();
            website.setLogs(queryLogBySiteId(website.getId()));
            websites.add(website);
        }
        return websites;
    }

    /**
     * 根据id查询网址
     * @param id id
     * @return Website
     */
    public Website queryById(int id){
        Website website =  websiteRepository.findById(id).get();
        website.setLogs(queryLogBySiteId(website.getId()));
        return website;
    }

    /**
     * 通过网站id 获取log
     * @param sid
     * @return
     */
    public List<Log> queryLogBySiteId(int sid){
        return logRepository.queryLogBySiteId(sid);
    }
}
