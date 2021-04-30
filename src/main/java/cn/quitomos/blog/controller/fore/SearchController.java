package cn.quitomos.blog.controller.fore;

import cn.quitomos.blog.adapter.PostsAdapter;
import cn.quitomos.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.beans.Encoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/search")
public class SearchController {

    private ArticleService articleService;

    /**
     * 搜索结果
     *
     * @param keyword 关键字
     * @param pageNum 页数
     * @param request 拿到uri contextPath
     * @return search.ftl, 需给出posts is_search keyword
     */
    @RequestMapping("/p/{page:\\d+}")
    public ModelAndView search(@RequestParam(value = "keyword") String keyword, @PathVariable("page") Integer pageNum, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("search");
        String contextPath = request.getContextPath();
        String uri = request.getRequestURI();
        PostsAdapter posts = articleService.getPostsByKeyword(contextPath, uri, keyword, pageNum);
        modelAndView.addObject("posts", posts);
        modelAndView.addObject("is_search", true);
        modelAndView.addObject("keyword", keyword);
        return modelAndView;
    }

    @RequestMapping("")
    public String searchFirstPage(@RequestParam("keyword") String keyword, RedirectAttributes attr) {
        attr.addAttribute("keyword", keyword);
        return "redirect:/search/p/1";
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
}
