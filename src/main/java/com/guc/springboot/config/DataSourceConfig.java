package com.guc.springboot.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;


/**
 * @Author guc
 * @Date 2020/1/19 10:37
 * @Description 数据源配置
 */
@Configuration
public class DataSourceConfig {
    @Bean
    @Primary // 该注解表示为主数据源
    @ConfigurationProperties("app.datasource.websites")//读取前缀为app.datasource.websites
    public DataSourceProperties websitesDataSourceProperties(){
        return new DataSourceProperties();
    }
    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.websites")
    public DataSource firstDataSource() {
        return websitesDataSourceProperties().initializeDataSourceBuilder().build();
    }


    @Bean  //这是第二个数据源，不能加@Primary注解了
    @ConfigurationProperties("app.datasource.second")
    public DataSourceProperties secondDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("app.datasource.second")
    public DataSource secondDataSource() {
        return secondDataSourceProperties().initializeDataSourceBuilder().build();
    }
}
