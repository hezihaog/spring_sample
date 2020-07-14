package com.itheima.test;

import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Xml配置方式的单元测试
 */
public class AccountServiceByXmlTest {
    private static final String SPRING_CONFIG = "bean3.xml";

    @Test
    public void testFindAll() {
        //获取容器
        ApplicationContext context = new ClassPathXmlApplicationContext(SPRING_CONFIG);
        //得到业务层对象
        IAccountService accountService = context.getBean("accountService", IAccountService.class);
        List<Account> allAccount = accountService.findAllAccount();
        for (Account account : allAccount) {
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne() {
        //获取容器
        ApplicationContext context = new ClassPathXmlApplicationContext(SPRING_CONFIG);
        //得到业务层对象
        IAccountService accountService = context.getBean("accountService", IAccountService.class);
        Account account = accountService.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testSave() {
        //获取容器
        ApplicationContext context = new ClassPathXmlApplicationContext(SPRING_CONFIG);
        //得到业务层对象
        IAccountService accountService = context.getBean("accountService", IAccountService.class);
        Account account = new Account();
        account.setName("小邋遢");
        account.setMoney(500f);
        accountService.saveAccount(account);
    }

    @Test
    public void testDelete() {
        //获取容器
        ApplicationContext context = new ClassPathXmlApplicationContext(SPRING_CONFIG);
        //得到业务层对象
        IAccountService accountService = context.getBean("accountService", IAccountService.class);
        accountService.deleteAccount(1);
    }

    @Test
    public void testUpdate() {
        //获取容器
        ApplicationContext context = new ClassPathXmlApplicationContext(SPRING_CONFIG);
        //得到业务层对象
        IAccountService accountService = context.getBean("accountService", IAccountService.class);
        Account account = new Account();
        account.setId(3);
        account.setMoney(60f);
        account.setName("小何");
        accountService.updateAccount(account);
    }
}