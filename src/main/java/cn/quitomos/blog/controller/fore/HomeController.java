package cn.quitomos.blog.controller.fore;

import cn.quitomos.blog.adapter.PostsAdapter;
import cn.quitomos.blog.adapter.interf.Post;
import cn.quitomos.blog.entity.Account;
import cn.quitomos.blog.entity.Article;
import cn.quitomos.blog.entity.Page;
import cn.quitomos.blog.service.AccountService;
import cn.quitomos.blog.service.ArticleService;
import cn.quitomos.blog.service.PageService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private AccountService accountService;
    private ArticleService articleService;
    private PageService pageService;

    /**
     * 首页
     *
     * @param request
     * @return index.ftl, 需给出is_index posts pageList
     */
    @RequestMapping("")
    public ModelAndView home(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("index");
        String contextPath = request.getContextPath();
        String uri = request.getRequestURI();

        Map<String, Object> params = new HashMap<>();
        params.put("articleOrder", "article_create_time desc");
        PageInfo<Article> articleList = articleService.listArticlePaged(1, 10, params);
        cn.quitomos.blog.adapter.interf.PageInfo<Post, Article> posts = new PostsAdapter(articleList, uri, contextPath);
        List<Page> pageList = pageService.list();
        modelAndView.addObject("is_index", true);
        modelAndView.addObject("posts", posts);
        modelAndView.addObject("pageList", pageList);
        return modelAndView;
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Autowired
    public void setPageService(PageService pageService) {
        this.pageService = pageService;
    }
}
