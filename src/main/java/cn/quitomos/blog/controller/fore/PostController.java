package cn.quitomos.blog.controller.fore;

import cn.quitomos.blog.adapter.PostAdapter;
import cn.quitomos.blog.adapter.interf.Post;
import cn.quitomos.blog.adapter.interf.Tag;
import cn.quitomos.blog.adapter.interf.User;
import cn.quitomos.blog.entity.Article;
import cn.quitomos.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/article")
public class PostController {

    private ArticleService articleService;

    /**
     * 文章页
     *
     * @param articleId 文章id
     * @param request
     * @return post.ftl, 需要给出文章作者user post is_post 文章标签tags prevPost nextPost
     */
    @RequestMapping(value = "/{articleId:\\d+}", method = RequestMethod.GET)
    public ModelAndView article(@PathVariable("articleId") Integer articleId, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("post");
        String contextPath = request.getContextPath();
        Post post = articleService.getPostById(articleId, contextPath);
        if (post == null) {
            modelAndView.setViewName("404");
            return modelAndView;
        }
        modelAndView.addObject("post", post);
        User user = articleService.getUserByPost(post, contextPath);
        modelAndView.addObject("user", user);
        modelAndView.addObject("is_post", true);
        List<Tag> tags = post.getTags();
        modelAndView.addObject("tags", tags);
        Post prevPost = articleService.getPrevious(post, contextPath);
        if (prevPost != null)
            modelAndView.addObject("prevPost", prevPost);
        Post nextPost = articleService.getNext(post, contextPath);
        if (nextPost != null)
            modelAndView.addObject("nextPost", nextPost);

        articleService.addViewCountByOne(articleId);
        return modelAndView;
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
}
