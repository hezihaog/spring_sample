package com.itheima.dao.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.util.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository("accountDao")
public class AccountDaoImpl implements IAccountDao {
    @Autowired
    @Qualifier("runner")
    private QueryRunner runner;

    /**
     * 连接工具
     */
    @Autowired
    private ConnectionUtils connectionUtils;

    public void save(Account account) {
        try {
            runner.update(connectionUtils.getThreadConnection(), "insert into account(name, money) values(?, ?)",
                    account.getName(), account.getMoney());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void update(Account account) {
        try {
            runner.update(connectionUtils.getThreadConnection(), "update account set name = ?, money = ? where id = ?",
                    account.getName(), account.getMoney(), account.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void delete(Integer accountId) {
        try {
            runner.update(connectionUtils.getThreadConnection(), "delete from account where id = ?", accountId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Account findById(Integer accountId) {
        try {
            return runner.query(connectionUtils.getThreadConnection(), "select * from account where id = ?",
                    new BeanHandler<Account>(Account.class),
                    accountId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Account> findAll() {
        try {
            return runner.query(connectionUtils.getThreadConnection(), "select * from account", new BeanListHandler<Account>(Account.class));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Account findAccountByName(String name) {
        try {
            List<Account> result = runner.query(connectionUtils.getThreadConnection(), "select * from account where name = ?",
                    new BeanListHandler<Account>(Account.class), name);
            if (result == null || result.size() == 0) {
                return null;
            }
            if (result.size() != 1) {
                throw new RuntimeException("查询错误，账户有多个");
            }
            return result.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}