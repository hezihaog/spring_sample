package com.itheima;

import com.itheima.service.IAccountService;
import com.itheima.test.CollectionInjectBean;
import com.itheima.test.ConstructorInjectBean;
import com.itheima.test.SetMethodInjectBean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
    /**
     * Xml配置Bean和注入
     *
     * ApplicationContext的3个常用实现类
     * 1.ClassPathXmlApplicationContext，可以加载类路径下的配置文件
     * 2.FileSystemXmlApplicationContext，可以加载磁盘任意路径下的配置文件
     * 3.AnnotationConfigApplicationContext，它是用于读取注解创建容器
     *
     * 问题
     * ApplicationContext：（单例对象适用，当bean的作用范围为多例时，会延迟加载，作用范围为单例时，会在容器加载时马上创建）
     *      创建容器时（读取配置文件），就将配置文件中配置的bean对象，马上反射创建，并放到容器中
     * BeanFactory：（多例对象适用）
     *      创建容器时，并不马上创建bean对象，为延迟加载的方式，获取时，再创建
     */
    public static void main(String[] args) {
        //1.创建Spring容器
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
//        ApplicationContext context = new FileSystemXmlApplicationContext("/Users/wally/Desktop/bean.xml");
        //2.获取Bean实例
//        IAccountService accountService = (IAccountService) context.getBean("account");
        IAccountService accountService = context.getBean("accountService", IAccountService.class);
        IAccountService accountService2 = context.getBean("accountService", IAccountService.class);
        //默认Bean是单例的
        System.out.println(accountService);
        System.out.println(accountService2);
        System.out.println(accountService == accountService2);

        //构造方法注入
        ConstructorInjectBean constructorInjectBean = context.getBean("constructorInjectBean", ConstructorInjectBean.class);
        constructorInjectBean.showInfo();

        //set方法注入
        SetMethodInjectBean setMethodInjectBean = context.getBean("setMethodInjectBean", SetMethodInjectBean.class);
        setMethodInjectBean.showInfo();

        //负责类型、集合类型注入
        CollectionInjectBean collectionInjectBean = context.getBean("collectionInjectBean", CollectionInjectBean.class);
        collectionInjectBean.showInfo();

        //手动关闭容器
        context.close();

        //BeanFactory，创建对象实例
//        Resource resource = new ClassPathResource("bean.xml");
//        BeanFactory factory = new XmlBeanFactory(resource);
//        accountService = factory.getBean("accountService", IAccountService.class);
//        System.out.println(accountService);
    }
}