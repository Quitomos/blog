package cn.quitomos.blog.adapter;

import cn.quitomos.blog.adapter.interf.User;

import java.io.Serializable;

public class UserAdapter implements User, Serializable {

    private static final long serialVersionUID = -5635838099253792402L;
    private final cn.quitomos.blog.entity.User user;

    private final String contextPath;

    public UserAdapter(cn.quitomos.blog.entity.User user, String contextPath) {
        this.user = user;
        this.contextPath = contextPath;
    }

    @Override
    public String getAvatar() {
        return contextPath + "/img/avatar/" + user.getUserAvatar();
    }

    @Override
    public String getNickname() {
        return user.getUserNickname();
    }
}
