package cn.quitomos.blog.service;

import cn.quitomos.blog.entity.User;

import java.util.List;

public interface UserService {

    /**
     * 根据userName或userEmail获取用户
     *
     * @param nameOrEmail
     * @return User
     */
    User getUserByNameOrEmail(String nameOrEmail);

    /**
     *根据userId获取用户
     *
     * @param id userId
     * @return User
     */
    User getUserById(int id);

    /**
     * 根据userId获取用户
     *
     * @param id userId
     * @param contextPath 上下文
     * @return 适配前台后的user
     */
    cn.quitomos.blog.adapter.interf.User getForeUserById(int id, String contextPath);

    /**
     * 根据userId更新user
     *
     * @param user
     */
    void updateUser(User user);

    /**
     * 创建新用户
     *
     * @param user user
     */
    void insertUser(User user);

    /**
     * 全部用户
     *
     * @return userList
     */
    List<User> list();

    /**
     * 删除用户
     *
     * @param id userId
     */
    void deleteUser(int id);
}
