package cn.quitomos.blog.mapper;

import cn.quitomos.blog.BaseTest;
import cn.quitomos.blog.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class UserMapperTest extends BaseTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    public void getUser() {
//        Map<String, Object> params = new HashMap<>();
//        params.put("username", "Quitomos");
//        System.out.println(userMapper.getUser(params));
//        System.out.println(userService.getUserByNameOrEmail("Quitomos"));
//        System.out.println(userService.getUserByNameOrEmail("quitomos@foxmail.com"));

    }
}
