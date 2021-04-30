package cn.quitomos.blog.service.impl;

import cn.quitomos.blog.adapter.AccountAdapter;
import cn.quitomos.blog.entity.Account;
import cn.quitomos.blog.mapper.AccountMapper;
import cn.quitomos.blog.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountMapper accountMapper;

    @Override
    public List<Account> list() {
        return accountMapper.list();
    }

    @Override
    public List<cn.quitomos.blog.adapter.interf.Account> listFore(String contextPath) {
        List<Account> accountEntityList = accountMapper.list();
        List<cn.quitomos.blog.adapter.interf.Account> ret = new ArrayList<>();
        for (Account a: accountEntityList) {
            ret.add(new AccountAdapter(a, contextPath));
        }
        return ret;
    }

    @Override
    public void saveAccount(Account account) {
        if (account.getAccountId() == null) {
            accountMapper.insertAccount(account);
        } else {
            accountMapper.updateAccount(account);
        }
    }

    @Override
    public void deleteAccount(Integer id) {
        accountMapper.deleteAccountById(id);
    }

    @Override
    public Account getAccountById(Integer id) {
        return accountMapper.getAccountById(id);
    }

    @Autowired
    public void setAccountMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }
}
