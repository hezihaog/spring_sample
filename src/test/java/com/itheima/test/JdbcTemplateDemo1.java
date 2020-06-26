package com.itheima.test;

import com.itheima.domain.Account;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

/**
 * JdbcTemplate的基本用法
 */
public class JdbcTemplateDemo1 {
    private static final String SPRING_CONFIG = "bean3.xml";

    /**
     * 普通使用
     */
    @Test
    public void test1() {
        //准备数据源
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/eesy");
        dataSource.setUsername("root");
        dataSource.setPassword("hezihao123");
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.execute("insert into account(name, money) values('ccc', 1000)");
    }

    /**
     * 配合Spring的ioc
     */
    @Test
    public void test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext(SPRING_CONFIG);
        JdbcTemplate template = context.getBean("jdbcTemplate", JdbcTemplate.class);
        template.execute("insert into account(name, money) values('bbb', 1000)");
    }

    /**
     * 测试增加
     */
    @Test
    public void testSave() {
        ApplicationContext context = new ClassPathXmlApplicationContext(SPRING_CONFIG);
        JdbcTemplate template = context.getBean("jdbcTemplate", JdbcTemplate.class);
        //保存
        template.update("insert into account(name, money) values(?, ?)", "eee", 333);
    }

    /**
     * 测试更新
     */
    @Test
    public void testUpdate() {
        ApplicationContext context = new ClassPathXmlApplicationContext(SPRING_CONFIG);
        JdbcTemplate template = context.getBean("jdbcTemplate", JdbcTemplate.class);
        //更新
        template.update("update account set name = ?, money = ? where id = ?", "test", 4567, 8);
    }

    /**
     * 测试删除
     */
    @Test
    public void testDelete() {
        ApplicationContext context = new ClassPathXmlApplicationContext(SPRING_CONFIG);
        JdbcTemplate template = context.getBean("jdbcTemplate", JdbcTemplate.class);
        //删除
        template.update("delete from account where id = ?", 8);
    }

    /**
     * 测试查询列表
     */
    @Test
    public void testQueryAll() {
        ApplicationContext context = new ClassPathXmlApplicationContext(SPRING_CONFIG);
        JdbcTemplate template = context.getBean("jdbcTemplate", JdbcTemplate.class);
        //查询所有，RowMapper的作用是将结果集的数据封装到Bean中
//        List<Account> resultList = template.query("select * from account where money > ?", new RowMapper<Account>() {
//            public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Account account = new Account();
//                account.setId(rs.getInt("id"));
//                account.setName(rs.getString("name"));
//                account.setMoney(rs.getFloat("money"));
//                return account;
//            }
//        }, 1000);
        //或者直接使用Spring提供的，推荐使用这个
        List<Account> resultList = template.query("select * from account where money > ?", new BeanPropertyRowMapper<Account>(Account.class), 1000);
        for (Account account : resultList) {
            System.out.println(account);
        }
    }

    /**
     * 测试查询单个数据
     */
    @Test
    public void testQueryOne() {
        ApplicationContext context = new ClassPathXmlApplicationContext(SPRING_CONFIG);
        JdbcTemplate template = context.getBean("jdbcTemplate", JdbcTemplate.class);
        //查询一个
        List<Account> resultList = template.query("select * from account where id = ?", new BeanPropertyRowMapper<Account>(Account.class), 2);
        if (resultList.isEmpty()) {
            System.out.println("没有查找到");
        } else {
            System.out.println(resultList.get(0));
        }
    }

    /**
     * 测试查询一行一列
     */
    @Test
    public void testQueryCount() {
        ApplicationContext context = new ClassPathXmlApplicationContext(SPRING_CONFIG);
        JdbcTemplate template = context.getBean("jdbcTemplate", JdbcTemplate.class);
        //查询返回一行一列（使用聚合函数，但不加group by子句）
        Long count = template.queryForObject("select count(*) from account where money > ?", Long.class, 80f);
        System.out.println("count：" + count);
    }
}