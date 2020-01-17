package com.guc.springboot.entity;

import javax.persistence.*;
import java.util.List;

/**
 * @Author guc
 * @Date 2020/1/9 11:47
 * @Description 网址bean
 */
@Table(name = "websites")
@Entity
public class Website {
    /**
     * 设置主键生成策略
     */
    //   GenerationType.AUTO 主键由程序控制
    //GenerationType.IDENTITY 主键由数据库自动生成（主要是自动增长类型）
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column
    private String name;
    @Column
    private String url;
    @Column
    private String country;

    @Transient  //不参与数据库映射
    private transient List<Log> logs;

    public Website() {
    }

    public Website(int id, String name, String url, String country) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.country = country;
    }

    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {

        return "{" +
                "id = " + id +
                ",name = " + name +
                ",url = " + url +
                ",country = " + country +
                ",logs = " + logToString(logs) +
                "}";
    }

    private String logToString(List<Log> logs) {
        StringBuilder sb = new StringBuilder();
        if (logs == null || logs.size()==0) sb.append("null");
        else
            for (Log log : logs) {
                sb.append(log.toString());
                sb.append(",");
            }
        int ifdelIndex = sb.lastIndexOf(",");
        if (ifdelIndex != -1)
            sb.deleteCharAt(ifdelIndex);
        return sb.toString();
    }
}
