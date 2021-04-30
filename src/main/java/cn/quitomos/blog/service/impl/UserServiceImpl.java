package cn.quitomos.blog.service.impl;

import cn.quitomos.blog.adapter.UserAdapter;
import cn.quitomos.blog.entity.User;
import cn.quitomos.blog.mapper.UserMapper;
import cn.quitomos.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Override
    public User getUserByNameOrEmail(String nameOrEmail) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", nameOrEmail);
        User user = userMapper.getUser(params);
        if (user == null) {
            params.remove("username");
            params.put("userEmail", nameOrEmail);
            user = userMapper.getUser(params);
        }
        return user;
    }

    @Override
    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    @Override
    public cn.quitomos.blog.adapter.interf.User getForeUserById(int id, String contextPath) {
        User user = userMapper.getUserById(id);
        cn.quitomos.blog.adapter.interf.User ret = new UserAdapter(user, contextPath);
        return ret;
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void insertUser(User user) {
        String avatar = user.getUserAvatar();
        if (avatar == null || avatar.equals(""))
            user.setUserAvatar("default.jpg");
        userMapper.insertUser(user);
    }

    @Override
    public List<User> list() {
        return userMapper.list();
    }

    @Override
    public void deleteUser(int id) {
        userMapper.deleteUserById(id);
        // 新增注销用户以占有该id
        User user = new User();
        user.setUserId(id);
        String userName = UUID.randomUUID().toString();
        userName = userName.substring(0, Math.min(20, userName.length())) + id;
        String userEmail = UUID.randomUUID().toString() + id;
        user.setUserName(userName);
        user.setUserEmail(userEmail);
        user.setUserNickname("已注销");
        this.insertUser(user);
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
