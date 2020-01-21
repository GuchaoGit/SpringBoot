package com.guc.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @Author guc
 * @Date 2020/1/19 10:47
 * @Description 第一个 websites 数据源配置
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        //设置Repository所在位置
        basePackages = {"com.guc.springboot.repository"},
        //设置entityManager工厂
        entityManagerFactoryRef = "entityManagerFactoryFirst",
        //设置事务管理器
        transactionManagerRef = "transactionManagerFirst"
)
public class WebsitesDataSourceConfiguration {
    /**
     * 数据源一
     */
    private final DataSource firstDataSource;

    /**
     * 构造方式注入依赖
     */
    @Autowired
    public WebsitesDataSourceConfiguration(@Qualifier("firstDataSource") DataSource firstDataSource) {
        this.firstDataSource = firstDataSource;
    }

    /**
     * 配置entityManager工厂
     */
    @Primary
    @Bean(name = "entityManagerFactoryFirst")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryFirst(EntityManagerFactoryBuilder builder) {
        return builder
                //设置数据源
                .dataSource(firstDataSource)
                //设置entity的包路径
                .packages("com.guc.springboot.entity")
                .build();
    }

    /**
     * 配置事务管理器
     */
    @Primary
    @Bean(name = "transactionManagerFirst")
    public PlatformTransactionManager transactionManagerFirst(EntityManagerFactoryBuilder builder) {
        //返回jpa事务管理器
        return new JpaTransactionManager(entityManagerFactoryFirst(builder).getObject());
    }
}
