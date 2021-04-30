package cn.quitomos.blog.mapper;

import cn.quitomos.blog.entity.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    /**
     * 通过userName或userEmail获取用户
     *
     * @param params
     * @return User
     */
    User getUser(Map<String, Object> params);

    /**
     * 通过userId获取用户
     *
     * @param id userId
     * @return User
     */
    User getUserById(int id);

    /**
     * 用户列表
     *
     * @return userList
     */
    List<User> list();

    /**
     * 通过userId更新user
     * 不提供更新userName和registerTime的功能
     *
     * @param user user
     */
    void updateUser(User user);

    /**
     * 创建新用户
     *
     * @param user user
     */
    void insertUser(User user);

    /**
     * 通过userId删除user
     *
     * @param id userId
     */
    void deleteUserById(int id);
}
