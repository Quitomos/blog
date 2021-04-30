package cn.quitomos.blog.controller.fore;

import cn.quitomos.blog.adapter.interf.Category;
import cn.quitomos.blog.adapter.interf.PageInfo;
import cn.quitomos.blog.adapter.interf.Post;
import cn.quitomos.blog.adapter.interf.Tag;
import cn.quitomos.blog.entity.Article;
import cn.quitomos.blog.service.ArticleService;
import cn.quitomos.blog.service.TagService;
import org.apache.xpath.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/tag")
public class TagController {

    private TagService tagService;
    private ArticleService articleService;

    /**
     * 全部标签页
     *
     * @return tags.ftl
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String tags() {
        return "tags";
    }

    /**
     * 一个标签页
     *
     * @param tagId 标签id
     * @param pageNum 页数
     * @param request 可获得uri contextPath
     * @return tag.ftl, 需给出posts tag
     */
    @RequestMapping(value = "/{tagId:\\d+}/p/{page:\\d+}", method = RequestMethod.GET)
    public ModelAndView tag(@PathVariable("tagId") Integer tagId, @PathVariable("page") Integer pageNum, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("tag");
        String contextPath = request.getContextPath();
        String uri = request.getRequestURI();
        Tag tag = tagService.getForeTagById(tagId, contextPath);
        modelAndView.addObject("tag", tag);
        PageInfo<Post, Article> posts = articleService.getPostsByTag(tag, contextPath, uri, pageNum);
        modelAndView.addObject("posts", posts);
        return modelAndView;
    }

    @RequestMapping(value = "/{tagId:\\d+}", method = RequestMethod.GET)
    public String tagFirstPage(@PathVariable("tagId") Integer tagId) {
        return "redirect:/tag/" + tagId + "/p/1";
    }

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
}
