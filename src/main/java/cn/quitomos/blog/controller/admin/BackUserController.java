package cn.quitomos.blog.controller.admin;

import cn.quitomos.blog.controller.base.BaseUploadFileController;
import cn.quitomos.blog.dto.LayuiJson;
import cn.quitomos.blog.dto.UploadFIleVO;
import cn.quitomos.blog.dto.UserDTO;
import cn.quitomos.blog.entity.User;
import cn.quitomos.blog.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台用户相关
 */
@Controller
@RequestMapping("/admin/user")
public class BackUserController extends BaseUploadFileController {

    private UserService userService;

    public BackUserController() {
        super("resource/assets/img/avatar/", ".bmp.jpg.jpeg.png.gif");
    }

    /**
     * 全部用户
     *
     * @param model 需提供userList
     * @return index.jsp
     */
    @RequestMapping("/list")
    public String list(Model model) {
        List<User> userList = userService.list();
        model.addAttribute("userList", userList);
        return "Admin/User/index";
    }

    /**
     * 新增用户界面
     *
     * @return insert.jsp
     */
    @RequestMapping("/insert")
    public String insert() {
        return "Admin/User/insert";
    }

    /**
     * 检查用户名是否已存在
     *
     * @param request "username" "id", id为0说明是insert, 否则说明是update
     * @return json:["code": 0/1(不存在/存在), "msg": 信息]
     */
    @RequestMapping("/checkUserName")
    @ResponseBody
    public String checkUserName(HttpServletRequest request) {
        Map<String, Object> json = new HashMap<>();
        String userName = request.getParameter("username");
        int userId = Integer.parseInt(request.getParameter("id"));
        User originalUser = null;
        if (userId != 0)
            originalUser = userService.getUserById(userId);
        User user = userService.getUserByNameOrEmail(userName);
        if (user == null || (originalUser != null && user.getUserName().equals(originalUser.getUserName()))) {
            json.put("code", 0);
            json.put("msg", "");
        } else {
            json.put("code", 1);
            json.put("msg", "该用户名已被注册!");
        }
        return new JSONObject(json).toString();
    }

    /**
     * 检查邮箱是否已存在
     *
     * @param request "email" "id"
     * @return json:["code": 0/1(不存在/存在), "msg": 错误信息]
     */
    @RequestMapping("/checkUserEmail")
    @ResponseBody
    public String checkUserEmail(HttpServletRequest request) {
        Map<String, Object> json = new HashMap<>();
        String email = request.getParameter("email");
        int userId = Integer.parseInt(request.getParameter("id"));
        User originalUser = null;
        if (userId != 0)
            originalUser = userService.getUserById(userId);
        User user = userService.getUserByNameOrEmail(email);
        if (user == null || (originalUser != null && user.getUserEmail().equals(originalUser.getUserEmail()))) {
            json.put("code", 0);
            json.put("msg", "");
        } else {
            json.put("code", 1);
            json.put("msg", "该邮箱已被注册!");
        }
        return new JSONObject(json).toString();
    }

    /**
     * 新增用户
     *
     * @param userDTO 接收的表单数据, 包含userName, userPassword, userNickName, userEmail, userRole, userAvatar
     * @return /list
     */
    @RequestMapping("/insertSubmit")
    public String insertSubmit(UserDTO userDTO) {
        // 这部分工作放到service层比较好
        User user = userDTO.getUser();
        String userName = user.getUserName();
        String userEmail = user.getUserEmail();
        if (userService.getUserByNameOrEmail(userName) == null && userService.getUserByNameOrEmail(userEmail) == null)
            userService.insertUser(user);
        return "redirect:/admin/user/list";
    }

    /**
     * 上传头像
     *
     * @param file 图片
     * @return json: ["code": 0/1(上传成功/失败), "data.title": 文件名, "msg": 提示信息]
     */
    @RequestMapping("/upload")
    @ResponseBody
    public LayuiJson uploadAvatar(MultipartFile file) {
        String uri = super.save(file);

        if (uri == null) {
            return new LayuiJson(LayuiJson.Result.FAIL, "不支持的文件类型", null, 0);
        }
        UploadFIleVO uploadFIleVO = new UploadFIleVO(null, uri);
        return new LayuiJson<>(LayuiJson.Result.SUCCESS, "上传成功", uploadFIleVO, 1);
    }

    /**
     * 返回编辑用户页面
     *
     * @param userId 要编辑的用户的userId
     * @param model model
     * @return  User/edit.jsp
     */
    @RequestMapping("/edit")
    public String edit(@RequestParam(value = "userid") int userId, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("backUser", user);
        return "Admin/User/edit";
    }

    /**
     * 提交用户修改
     *
     * @param userDTO userDTO
     * @return /list
     */
    @RequestMapping("/editSubmit")
    public String editSubmit(UserDTO userDTO) {
        User user = userDTO.getUser();
        userService.updateUser(user);
        return "redirect:/admin/user/list";
    }

    /**
     * 删除用户
     *
     * @param userId 要删除的用户的id
     * @return /list
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam(value = "userid") int userId) {
        userService.deleteUser(userId);
        return "redirect:/admin/user/list";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
