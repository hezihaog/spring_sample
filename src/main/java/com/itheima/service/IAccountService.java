package com.itheima.service;

import com.itheima.domain.Account;

import java.util.List;

public interface IAccountService {
    /**
     * 保存账户
     */
    void saveAccount(Account account);

    /**
     * 更新账户
     */
    void updateAccount(Account account);

    /**
     * 删除账户
     */
    void deleteAccount(Integer accountId);

    /**
     * 根据 id 查询账户
     */
    Account findAccountById(Integer accountId);

    /**
     * 查询所有账户
     */
    List<Account> findAllAccount();
}