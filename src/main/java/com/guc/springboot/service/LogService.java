package com.guc.springboot.service;

import com.guc.springboot.entity.Log;
import com.guc.springboot.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author guc
 * @Date 2020/1/17 10:33
 * @Description 提供数据  作为 service 层
 */
@Service
public class LogService {
    @Autowired
    private LogRepository logRepository;

    public List<Log> queryLogBySiteId(int id){
        return logRepository.queryLogBySiteId(id);
    }

}
