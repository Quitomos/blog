package cn.quitomos.blog.adapter;

import cn.quitomos.blog.adapter.interf.Account;

import java.io.Serializable;

public class AccountAdapter implements Account, Serializable {

    private static final long serialVersionUID = -3388442703046799851L;
    private final cn.quitomos.blog.entity.Account account;
    private final String contextPath;

    public AccountAdapter(cn.quitomos.blog.entity.Account account, String contextPath) {
        this.account = account;
        this.contextPath = contextPath;
    }

    @Override
    public String getAccountUrl() {
        return account.getAccountUrl();
    }

    @Override
    public String getAccountTitle() {
        return account.getAccountName();
    }

    @Override
    public String getAccountIcon() {
        return contextPath + "/img/account/" + account.getAccountIcon();
    }
}
