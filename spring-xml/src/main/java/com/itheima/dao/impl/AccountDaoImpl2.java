package com.itheima.dao.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AccountDaoImpl2 implements IAccountDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Account account) {
        jdbcTemplate.update("insert into account(name, money) values(?, ?)", account.getName(), account.getMoney());
    }

    public void update(Account account) {
        jdbcTemplate.update("update account set name = ?, money = ? where id = ?",
                account.getName(), account.getMoney(), account.getId());
    }

    public void delete(Integer accountId) {
        jdbcTemplate.update("delete from account where id = ?", accountId);
    }

    public Account findById(Integer accountId) {
        List<Account> resultList = jdbcTemplate.query("select * from account where id = ?",
                new BeanPropertyRowMapper<Account>(Account.class), accountId);
        if (resultList.isEmpty()) {
            throw new RuntimeException("没有查询到");
        }
        if (resultList.size() > 1) {
            throw new RuntimeException("结果集不唯一");
        }
        return resultList.get(0);
    }

    public List<Account> findAll() {
        return jdbcTemplate.query("select * from account", new BeanPropertyRowMapper<Account>(Account.class));
    }

    public Account findAccountByName(String name) {
        List<Account> resultList = jdbcTemplate.query("select * from account where name = ?", new BeanPropertyRowMapper<Account>(Account.class), name);
        if (resultList.isEmpty()) {
            throw new RuntimeException("查找不到结果");
        }
        if (resultList.size() > 1) {
            throw new RuntimeException("结果集不唯一");
        }
        return resultList.get(0);
    }
}