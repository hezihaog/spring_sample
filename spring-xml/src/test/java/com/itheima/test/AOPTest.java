package com.itheima.test;

import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * AOP测试
 */
public class AOPTest {
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
}