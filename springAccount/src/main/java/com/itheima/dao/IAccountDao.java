package com.itheima.dao;

import com.itheima.domain.Account;
import sun.reflect.generics.scope.Scope;

import java.util.List;

/**
 * 账户的持久层接口
 */
public interface IAccountDao {

    /**
     * 查询所有
     * @return
     */
    List<Account> findAllAccount();

    /**
     * 查询一个
     * @return
     */
    Account findAccountById(Integer accountId);

    /**
     * 保存
     * @param account
     */
    void saveAccount(Account account);

    /**
     * 更新
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 删除
     * @param acccountId
     */
    void deleteAccount(Integer acccountId);

    /**
     * 根据name查询账户 查询有结果就返回account 没有就返回null
     * @param name
     * @return
     */
    Account findAccountByName(String name);


}
