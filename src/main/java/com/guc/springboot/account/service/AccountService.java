package com.guc.springboot.account.service;

import com.guc.springboot.account.entity.Account;
import com.guc.springboot.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author guc
 * @Date 2020/1/19 11:32
 * @Description 账户service
 */
@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    /**
     * 查询所有账户
     *
     * @return
     */
    public List<Account> queryAll() {
        List<Account> accounts = new ArrayList<>();
        Iterable<Account> iterable = accountRepository.findAll();
        for (Iterator<Account> iterator = iterable.iterator(); iterator.hasNext(); ) {
            accounts.add(iterator.next());
        }
        return accounts;
    }

    /**
     * 转账
     *
     * @param outer 汇款人
     * @param inner 收款人
     * @param money 交易金额
     * @return 0:转账成功 -1：转账失败
     */
    @Transactional(rollbackFor = Exception.class) //添加事务 异常回滚  通过AOP也可实现
    public int transfer(String outer, String inner, int money) {
        int out = accountRepository.out(outer, money);
        int in = accountRepository.in(inner, money);
        if (out == -1 || in == -1) return -1;
        return 0;
    }
}
