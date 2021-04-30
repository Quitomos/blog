package cn.quitomos.blog.mapper;

import cn.quitomos.blog.entity.Account;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountMapper {

    /**
     * 外链账户列表
     *
     * @return accountList
     */
    List<Account> list();

    /**
     * 通过accountId获得外链账户
     *
     * @param id accountId
     * @return account
     */
    Account getAccountById(int id);

    /**
     * 添加外链账户
     *
     * @param account 添加的外链账户
     */
    void insertAccount(Account account);

    /**
     * 根据accountId修改外链账户
     *
     * @param account 修改的外链账户
     */
    void updateAccount(Account account);

    /**
     * 根据accountId删除外链账户
     *
     * @param id categoryId
     */
    void deleteAccountById(int id);
}
