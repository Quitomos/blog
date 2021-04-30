package cn.quitomos.blog.controller.fore;

import cn.quitomos.blog.adapter.interf.Post;
import cn.quitomos.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PageController {

    private ArticleService articleService;

    @RequestMapping("/archive")
    public String archive() {
        return "archives";
    }

    @RequestMapping("/sitemap")
    public ModelAndView sitemap(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("common/web/sitemap_html");
        List<Post> posts = articleService.getRecentPosts(50, request.getContextPath());
        modelAndView.addObject("posts", posts);
        return modelAndView;
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
}
