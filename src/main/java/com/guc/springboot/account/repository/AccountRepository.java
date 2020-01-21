package com.guc.springboot.account.repository;

import com.guc.springboot.account.entity.Account;
import com.guc.springboot.entity.Website;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author guc
 * @Date 2020/1/19 11:25
 * @Description 账户Repository
 */
public interface AccountRepository extends CrudRepository<Account,Integer> {
    /**
     * 汇款
     * @param outer 汇款人
     * @param money 汇款金额
     *              占位符形式
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Account a set a.money = a.money - ?2 where a.username = ?1")
    int out( String outer, int money);

    /**
     * 收款
     * @param inner 收款人
     * @param money 收款金额
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Account a set a.money = a.money + ?2 where a.username =?1")
    int in(String inner, int money);

}
