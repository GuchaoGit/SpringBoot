package com.guc.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

//通过@ImportResource加载xml配置文件
//@ImportResource(value = "classpath:config.xml")
//@SpringBootApplication注解相当于同时使用@EnableAutoConfiguration、@ComponentScan、@Configurations三个注解
//@EnableAutoConfiguration用于打开SpringBoot自动配置，而其余注解为Spring注解
@SpringBootApplication
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

}
