package com.itheima;

import com.itheima.service.IAccountService;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 注解配置Bean和注入
 */
public class Client2 {
    public static void main(String[] args) {
        //1.创建Spring容器
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        //2.获取Bean实例
        IAccountService accountService = (IAccountService) context.getBean("accountService");
        IAccountService accountService2 = (IAccountService) context.getBean("accountService");
        System.out.println(accountService);
        System.out.println(accountService2);
        System.out.println(accountService == accountService2);
        System.out.println(accountService.findAllAccount());
        //手动关闭容器
        context.close();
    }
}