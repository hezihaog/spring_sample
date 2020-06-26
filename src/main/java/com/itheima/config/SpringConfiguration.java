package com.itheima.config;

import org.springframework.context.annotation.*;

/**
 * 配置类，作用和bean.xml是一样的
 * <p>
 * 新注解：
 * 1.Configuration：指定当前类是一个配置类
 * <p>
 * 2.ComponentScan：
 * 作用：用于通过注解指定Spring在创建容器时，要扫描的包
 * 细节：当配置类作为AnnotationConfigApplicationContext对象创建参数时，该注解可以不写
 * 属性：
 * value：和basePackages的作用是一样的，都是指定创建容器时要扫描的包
 * 我们使用该注解，就等同于在xml中配置了：<context:component-scan base-package="com.itheima"/>
 * <p>
 * <p>
 * 3.Bean
 * 作用：用于把当前方法的返回值作为bean对象存入spring的ioc容器中
 * 属性：name：用于指定bean的id，不写时id为默认当前方法名称
 * 细节：当我们使用注解配置方法时，spring回去容器中查找有没有可用的bean
 * 查找的方式和autowired注解的作用是一样的
 * <p>
 * 4.Import
 * 作用：用于导入其他的配置类
 * 属性：value：用于配置其他配置类的字节码，有Import注解的类就是父配置，而导入的配置类就是子配置类
 * <p>
 * 5.PropertySource
 * 作用:用于指定properties文件的位置
 * 属性：
 * value：指定文件的名称和文件的路径
 * 关键字：classpath，表示类路径下，如果有多层文件夹（包）下，则使用/分开
 * 6.@EnableAspectJAutoProxy
 * 作用：用于开启AOP的代理
 */
@Configuration
@Import(value = {JdbcConfig.class})
@PropertySource("classpath:jdbcConfig.properties")
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.itheima"})
public class SpringConfiguration {
}