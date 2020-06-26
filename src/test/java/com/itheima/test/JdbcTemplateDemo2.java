package com.itheima.test;

import com.itheima.dao.IAccountDao;
import com.itheima.dao.impl.AccountDaoImpl2;
import com.itheima.domain.Account;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * JdbcTemplate的Dao操作
 */
public class JdbcTemplateDemo2 {
    /**
     * 普通使用
     */
    @Test
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean3.xml");
        IAccountDao dao = context.getBean("accountDao2", AccountDaoImpl2.class);
        //测试查询
        Account account = dao.findById(2);
        System.out.println(account);
        //测试更新
        account.setMoney(10000f);
        dao.update(account);
        System.out.println(account);
    }
}