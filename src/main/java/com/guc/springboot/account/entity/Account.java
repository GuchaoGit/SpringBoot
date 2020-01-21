package com.guc.springboot.account.entity;

import javax.persistence.*;

/**
 * @Author guc
 * @Date 2020/1/10 16:33
 * @Description 账户
 */
@Table(name = "account")
@Entity
public class Account {
    /**
     * 设置主键生成策略
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column
    private String username;
    @Column
    private int money;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "{" +
                "id = " + id +
                ",name = " + username +
                ",money = " + money +
                "}";
    }
}
