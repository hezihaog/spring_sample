package com.itheima.test;

import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Spring整合Junit测试-Xml方式
 *
 * 1.spring整合junit，导入spring-test坐标
 * 2.使用Junit提供的注解，把原有的main方法，替换成spring提供的（@RunWith注解）
 * 3.告知spring的运行器，spring创建ioc容器是通过xml还是注解等，并说明位置(@ContextConfiguration)
 * 1）locations：指定xml文件的位置，加上classpath关键字，表示在类路径下
 * 2）classes：指定注解类所在的位置
 * <p>
 * 当我们使用spring5.x版本时，要求junit的jar必须是4.12及以上
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean3.xml")
public class SpringJunitXmlTest {
    @Autowired
    private IAccountService accountService;

    @Test
    public void testFindAll() {
        List<Account> allAccount = accountService.findAllAccount();
        for (Account account : allAccount) {
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne() {
        Account account = accountService.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testSave() {
        Account account = new Account();
        account.setName("小邋遢");
        account.setMoney(500f);
        accountService.saveAccount(account);
    }

    @Test
    public void testDelete() {
        accountService.deleteAccount(1);
    }

    @Test
    public void testUpdate() {
        Account account = new Account();
        account.setId(3);
        account.setMoney(60f);
        account.setName("小何");
        accountService.updateAccount(account);
    }
}