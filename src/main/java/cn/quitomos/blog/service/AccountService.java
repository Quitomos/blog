package cn.quitomos.blog.service;

import cn.quitomos.blog.entity.Account;

import java.util.List;

public interface AccountService {

    /**
     * 外链账户列表
     * @return accountList
     */
    List<Account> list();

    /**
     * 外链账户列表
     *
     * @return 适配前台的accounts
     */
    List<cn.quitomos.blog.adapter.interf.Account> listFore(String contextPath);
    /**
     * 添加/修改外链账户
     *
     * @param account account
     */
    void saveAccount(Account account);

    /**
     * 删除外链账户
     *
     * @param id accountId
     */
    void deleteAccount(Integer id);

    /**
     * 根据外链账户id获取外链账户
     *
     * @param id accountId
     * @return
     */
    Account getAccountById(Integer id);
}
