package com.guc.springboot.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

/**
 * @Author guc
 * @Date 2020/1/16 15:23
 * @Description 修改访问端口为 8080，也可以通过 Java 代码来实现
 */
//@Configuration表示该类为配置类，该注解可以被@ComponentScan扫描到
//@Configuration
public class ServletConfig implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        //设置端口为8083
        factory.setPort(8083);
    }
}
