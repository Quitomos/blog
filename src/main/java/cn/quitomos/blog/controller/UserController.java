package cn.quitomos.blog.controller;

import cn.quitomos.blog.adapter.UserAdapter;
import cn.quitomos.blog.entity.User;
import cn.quitomos.blog.enums.UserRole;
import cn.quitomos.blog.service.UserService;
import cn.quitomos.blog.util.IPUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录用户相关
 */
@Controller
public class UserController {

    private UserService userService;

    /**
     * 登录页面
     *
     * @return login.jsp
     */
    @RequestMapping("/login")
    public String login(HttpSession session) {
        session.removeAttribute("user");
        session.removeAttribute("foreUser");
        return "Admin/login";
    }

    /**
     * 登录验证
     * 通过用户名或邮箱登录
     * 若request里有rememberme,则设置cookie
     *
     *
     * @param request "username", "password", "rememberme"
     * @param response
     * @return json: ["code": 0(验证失败)/1(验证成功), "msg": 错误信息, "redirectUrl": 成功跳转页面]
     */
    @RequestMapping("/loginVerify")
    @ResponseBody
    public String loginVerify(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberme");

        Map<String, Object> json = new HashMap<>();

        User user = userService.getUserByNameOrEmail(username);

        if (user == null) {
            json.put("code", 0);
            json.put("msg", "用户名/邮箱错误");
            return new JSONObject(json).toString();
        }

        if (!password.equals(user.getUserPassword())) {
            json.put("code", 0);
            json.put("msg", "密码错误");
            return new JSONObject(json).toString();
        }
        json.put("code", 1);

        if (rememberMe != null) {
            Cookie nameCookie = new Cookie("username", username);
            Cookie passwordCookie = new Cookie("password", password);
            // 存7天cookie
            nameCookie.setMaxAge(60 * 60 * 24 * 7);
            passwordCookie.setMaxAge(60 * 60 * 24 * 7);

            response.addCookie(nameCookie);
            response.addCookie(passwordCookie);
        }

        // 如果登录的是管理员,则进入后台,否则进入首页
        UserRole userRole = user.getUserRole();
        json.put("redirectUrl", request.getContextPath() + (userRole == UserRole.ADMIN? "/admin": "/"));

        // 需要更新最后登入时间和最后登入ip
        user.setUserLastLoginTime(new Date());
        user.setUserLastLoginIp(IPUtil.getIPAddress(request));
        userService.updateUser(user);

        if (userRole == UserRole.ADMIN)
            request.getSession().setAttribute("backUser", user);
        return new JSONObject(json).toString();
    }

    /**
     * 注册页面
     *
     * @return register.jsp
     */
    @RequestMapping("/register")
    public String register() {
        return "Admin/register";
    }

    /**
     * 注册验证
     * 若插入数据库失败,则返回信息
     *
     * @param request "username" "nickname" "email" password"
     * @return json: ["code": 0/1(失败/成功), "msg": 失败信息
     */
    @RequestMapping("/registerSubmit")
    @ResponseBody
    public String registerSubmit(HttpServletRequest request) {
        Map<String, Object> json = new HashMap<>();
        String username = request.getParameter("username");
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (userService.getUserByNameOrEmail(username) != null) {
            json.put("code", 0);
            json.put("msg", "用户名已存在");
            return new JSONObject(json).toString();
        }
        if (userService.getUserByNameOrEmail(email) != null) {
            json.put("code", 0);
            json.put("msg", "邮箱已被注册");
            return new JSONObject(json).toString();
        }

        User user = new User();
        user.setUserName(username);
        user.setUserNickname(nickname);
        user.setUserEmail(email);
        user.setUserPassword(password);
        user.setUserRegisterTime(new Date());
        user.setUserLastLoginTime(new Date());

        userService.insertUser(user);
        json.put("code", 1);
        return new JSONObject(json).toString();
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        session.removeAttribute("foreUser");
        return "redirect:/";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
