# SpringBoot
SpringBoot入门学习

## Spring Boot 
Spring Boot 其实就是 Spring，学过 Spring 的同学一定都知道，即使是一个很简单的程序，Spring 都需要大量的配置。而 Spring Boot 将我们从配置文件中解放了出来，Spring Boot 提供了大量的默认配置，我们只需要少数的配置甚至不配置，就可以建立起来一个 Spring Boot 应用。  
###Spring Boot的优点  
* 使用 [Spring Initializr](https://start.spring.io/ ) 可以在几秒钟就配置好一个 Spring Boot 应用。
* 对大量的框架都可以无缝集成，基本不需要配置或者很少的配置就可以运行。
* 简化的安全性。
* 丰富的 SQL 和 NoSQL 支持。
* 嵌入式 Servlet 容器的支持，如：Tomcat，Jetty 和 Undertow。
* 提高开发、生产效率，如热部署等功能。
* 提供监控功能，如跟踪，指标和健康状况。

## 搭建一个Spring boot 应用
[Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/2.0.4.RELEASE/reference/htmlsingle/#using-boot-dependency-management)

## Spring Boot 配置文件简介
###application.properties配置文件
* src/main/resources 目录下有一个 application.properties 配置文件(如果没有就手动创建一个)。这就是SpringBoot默认配置文件
格式：  
  * 键=值
  * properties 文件中注释使用#开头    
```
#端口
server.port=8080
```
注意：这里只能访问 application.properties 中的属性，如果是其他自定义的配置文件中的属性是访问不到的，还需要其他的处理。
```
//加载classpath目录下的guc.properties文件
@PropertySource(value = "classpath:guc.properties")
```
### Java配置
```
@Configuration
public class ServletConfig implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        //设置端口为8083
        factory.setPort(8083);
    }
}
```
### xml 配置（不推荐使用）
```
程序入口配置
//通过@ImportResource加载xml配置文件
@ImportResource(value = "classpath:config.xml")

config.xml 内容
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="servletConfig" class="com.guc.springboot.config.ServletConfig"/>
</beans>
```

## Spring Boot Web支持
###Spring Boot MVC基本配置
* Servlet容器相关的属性配置
* Spring mvc 相关配置
  *  比如spring.mvc.view.prefix 和 spring.mvc.view.suffix,这两个属性用于配置视图文件的前缀和后缀  
* SpringBoot 的静态资源默认目录为/static、/public、/resources、和/META-INF/resources，默认映射路径都是/。SpringBoot 默认会按照META/resources > resources > static > public 的优先级寻找对应的资源文件并返回第一个找到的文件。
### Spring Boot 静态资源(static 目录下)
* http://localhost:8080/view/hello.html  <=> http://localhost:8080/welcome
* 配置自己的静态资源路径 （既可通过映射访问，也可通过静态资源路径访问资源）
  * 通过实现WebMvcConfigurer接口的addResourceHandlers方法来自定义静态资源。 打开SpringbootApplication.java，添加以下内容：
```
  
   //设置配置类
	@Configuration
	static class WebConfig implements WebMvcConfigurer {

		//重写addResourceHandlers方法
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			//设置静态资源映射路径为/**，资源位置为类路径下的upload  添加两个静态路径
			registry.addResourceHandler("/**").addResourceLocations("classpath:/upload/").addResourceLocations("classpath:/static/");
		}
	}
```
  * 使用 application.properties 配置
 ```
 #多个配置之间使用,分割  注意这个属性会使默认的配置失效
 spring.resources.static-locations=classpath:/upload/,classpath:/static
 ```
  ## Spring Boot 事务控制(采用 Spring-Date-JPA 来实现)
数据库事务(Database Transaction) ，是指作为单个逻辑工作单元执行的一系列操作，要么完全地执行，要么完全地不执行。一个逻辑工作单元要成为事务，必须满足所谓的 ACID（原子性、一致性、隔离性和持久性）属性。  
* 脏读：一个事务读取到了另一个事务没有提交的事务
* 不可重复读：一个事务中对同一行记录读取两次得到不同的结果
* 幻影读：一个事务两个相同的查询获得的结果不相同  
不可重复读和幻影读的区别主要在于不可重复读是由于更新和删除操作造成的，而幻影读是由于插入操作造成的。  

[Spring 事务](https://github.com/GuchaoGit/firstSpring.git) 一种是编程式事务，一种是声明式事务，但是都需要配置事务管理器  
SpringBoot 的事务管理十分简单，只需要一个@Transactional 注解就可以了，当然必须是使用主流 ORM（Object Relational Mapping 对象-关系映射）框架。

## Spring Boot 中 aop 运用
AOP 为 Aspect Oriented Programming 的缩写，意为：面向切面编程，通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。  
常用的aop注解：  
* @Before 在方法前执行
* @After 在方法执行后执行
* @AfterReturning 在结果返回后执行
* @AfterThrowing 在抛出异常后执行
* @Around 环绕通知，相当于同时使用@Before 和@After  
aop进行事务控制

## Spring Boot 多数据源的配置
开发项目的时候只使用了单个数据库，而多数据源可以帮助使用多个数据库或者不同类型的数据库。  
Spring Data JPA之Query注解与Modifying注解
* 命名空间形式
```
    @Query("select t from Log t where t.site_id = :site_id")
    List<Log> queryLogBySiteId(@Param("site_id")int sid); //自定义方法
```
* 占位符形式
```
    //要想使用UPDATE或DELETE语句则需要在@Query注解上@Modifying注解，以通知该JPQL为更新或删除操作。
    @Modifying(clearAutomatically = true)
    @Query("update Account a set a.money = a.money + ?2 where a.username =?1")
    int in(String inner, int money);
```

## Spring Boot 整合Mybatis开发
[MyBatis](https://github.com/GuchaoGit/MyBatis.git)框架基础

