package cn.quitomos.blog.entity;

import cn.quitomos.blog.enums.UserRole;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 6053844347312686627L;

    public User() {

    }

    public User(Integer userId,
                String userName,
                String userPassword,
                String userNickname,
                String userEmail,
                Integer userRole,
                String userAvatar,
                Integer userStatus) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userNickname = userNickname;
        this.userEmail = userEmail;
        this.userAvatar = userAvatar;
        if (userRole != null) this.userRole = UserRole.getUserRole(userRole);
        this.userStatus = userStatus;
    }

    private Integer userId;

    private String userName;

    private String userPassword;

    private String userNickname;

    private String userEmail;

    private String userLastLoginIp;

    private Date userRegisterTime;

    private Date userLastLoginTime;

    private Integer userStatus;

    private UserRole userRole;

    private String userAvatar;

}
