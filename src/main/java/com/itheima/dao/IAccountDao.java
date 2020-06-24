package com.itheima.dao;

import com.itheima.domain.Account;

import java.util.List;

public interface IAccountDao {
    /**
     * 保存
     */
    void save(Account account);

    /**
     * 更新
     */
    void update(Account account);

    /**
     * 删除
     */
    void delete(Integer accountId);

    /**
     * 根据 id 查询
     */
    Account findById(Integer accountId);

    /**
     * 查询所有
     */
    List<Account> findAll();
}