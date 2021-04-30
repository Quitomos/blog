package cn.quitomos.blog.dto;

import cn.quitomos.blog.entity.User;
import lombok.Data;

@Data
public class UserDTO {

    private Integer userId;

    private String userName;

    private String userPassword;

    private String userNickname;

    private String userEmail;

    private Integer userRole;

    private String userAvatar;

    private Integer userStatus;

    /**
     * 从DTO新建User
     *
     * @return newUser
     */
    public User getUser() {
        return new User(userId, userName, userPassword, userNickname, userEmail, userRole, userAvatar, userStatus);
    }
}
