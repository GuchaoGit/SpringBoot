package com.guc.springboot.repository;

import com.guc.springboot.entity.Log;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Author guc
 * @Date 2020/1/17 10:27
 * @Description 作为 dao 层
 */
public interface LogRepository extends CrudRepository<Log,Integer> {
    //命名空间形式
    @Query("select t from Log t where t.site_id = :site_id")
    List<Log> queryLogBySiteId(@Param("site_id")int sid); //自定义方法
}
