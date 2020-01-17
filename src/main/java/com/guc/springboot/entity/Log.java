package com.guc.springboot.entity;

import javax.persistence.*;

/**
 * @Author guc
 * @Date 2020/1/14 9:26
 * @Description 日志
 */
/**
 * 设置表名为log，并且标记该类为实体类
 */
@Table(name = "log")
@Entity
public class Log {
    /**
     * 设置主键生成策略
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column
    private int site_id;
    @Column
    private int count;
    @Column
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSite_id() {
        return site_id;
    }

    public void setSite_id(int site_id) {
        this.site_id = site_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "{" +
                "id = " +id+
                ",site_id = " +site_id+
                ",count = " +count+
                ",date = " +date+
                "}";
    }
}
