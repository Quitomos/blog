package cn.quitomos.blog.controller.admin;

import cn.quitomos.blog.entity.Article;
import cn.quitomos.blog.entity.Comment;
import cn.quitomos.blog.entity.User;
import cn.quitomos.blog.service.ArticleService;
import cn.quitomos.blog.service.CommentService;
import cn.quitomos.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 后台首页相关
 */
@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    private ArticleService articleService;
    private CommentService commentService;
    private UserService userService;

    /**
     * 后台首页
     *
     * @param model 需要给出articleList,commentList,
     * @return index.jsp
     */
    @RequestMapping("")
    public String index(Model model) {
        List<Article> articleList = articleService.getRecentArticles(5);
        List<Comment> commentList = commentService.getRecentComments(5);
        model.addAttribute("articleList", articleList);
        model.addAttribute("commentList", commentList);
        return "Admin/index";
    }

    /**
     * 用户信息页
     *
     * @param request 可获得parameter: userid
     * @param model 需要给出user
     * @return profile.jsp
     */
    @RequestMapping("/user/profile")
    public String profile(HttpServletRequest request, Model model) {
        String userId = request.getParameter("userid");
        User user = userService.getUserById(Integer.parseInt(userId));
        model.addAttribute("BackUser", user);
        return "Admin/User/profile";
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
