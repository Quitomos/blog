package cn.quitomos.blog.adapter.interf;

import java.util.Date;
import java.util.List;

public interface Comment {

    boolean isAllowNotification();

    List<Comment> getChildren();

    int getId();

    String getAuthor();

    String getEmail();

    String getIpAddress();

    String getAuthorUrl();

    String getGravatarMd5();

    String getContent();

    String getUserAgent();

    int getParentId();

    boolean isIsAdmin();

    Date getCreateTime();

    String getAvatar();

    String getStatus();
}
