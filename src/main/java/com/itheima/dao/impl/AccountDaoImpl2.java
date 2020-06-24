package com.itheima.dao.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("accountDao2")
public class AccountDaoImpl2 implements IAccountDao {
    public void save(Account account) {
    }

    public void update(Account account) {
    }

    public void delete(Integer accountId) {
    }

    public Account findById(Integer accountId) {
        return null;
    }

    public List<Account> findAll() {
        System.out.println("AccountDaoImpl2 => findAll()...");
        return null;
    }
}