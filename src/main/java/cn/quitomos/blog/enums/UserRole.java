package cn.quitomos.blog.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN(0),
    STANDARD(1);

    private int code;

    UserRole(int code) {
        this.code = code;
    }

    /**
     * 通过code获取UsrRole
     *
     * @param code code
     * @return UserRole
     */
    public static UserRole getUserRole(int code) {
        UserRole[] userRoles = UserRole.values();
        for (UserRole userRole: userRoles) {
            if (userRole.getCode() == code)
                return userRole;
        }
        return null;
    }
}
