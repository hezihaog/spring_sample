package com.itheima.test;

import com.itheima.config.SpringConfiguration;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * JavaConfig配置方式的的单元测试
 */
public class AccountServiceByConfigTest {
    private static final Class<?> SPRING_CONFIG = SpringConfiguration.class;

    @Test
    public void testFindAll() {
        //获取容器
        ApplicationContext context = new AnnotationConfigApplicationContext(SPRING_CONFIG);
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
        ApplicationContext context = new AnnotationConfigApplicationContext(SPRING_CONFIG);
        //得到业务层对象
        IAccountService accountService = context.getBean("accountService", IAccountService.class);
        Account account = accountService.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testSave() {
        //获取容器
        ApplicationContext context = new AnnotationConfigApplicationContext(SPRING_CONFIG);
        //得到业务层对象
        IAccountService accountService = context.getBean("accountService", IAccountService.class);
        Account account = new Account();
        account.setName("小邋遢2");
        account.setMoney(500f);
        accountService.saveAccount(account);
    }

    @Test
    public void testDelete() {
        //获取容器
        ApplicationContext context = new AnnotationConfigApplicationContext(SPRING_CONFIG);
        //得到业务层对象
        IAccountService accountService = context.getBean("accountService", IAccountService.class);
        accountService.deleteAccount(1);
    }

    @Test
    public void testUpdate() {
        //获取容器
        ApplicationContext context = new AnnotationConfigApplicationContext(SPRING_CONFIG);
        //得到业务层对象
        IAccountService accountService = context.getBean("accountService", IAccountService.class);
        Account account = new Account();
        account.setId(3);
        account.setMoney(60f);
        account.setName("小何");
        accountService.updateAccount(account);
    }
}