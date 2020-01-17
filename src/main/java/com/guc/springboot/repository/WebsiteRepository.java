package com.guc.springboot.repository;

import com.guc.springboot.entity.Website;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @Author guc
 * @Date 2020/1/17 10:04
 * @Description 作为 dao 层
 */
public interface WebsiteRepository extends CrudRepository<Website,Integer> {
}
