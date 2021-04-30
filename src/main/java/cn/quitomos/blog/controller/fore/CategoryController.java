package cn.quitomos.blog.controller.fore;

import cn.quitomos.blog.adapter.interf.Category;
import cn.quitomos.blog.adapter.interf.PageInfo;
import cn.quitomos.blog.adapter.interf.Post;
import cn.quitomos.blog.entity.Article;
import cn.quitomos.blog.service.ArticleService;
import cn.quitomos.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;
    private ArticleService articleService;

    /**
     * 全部分类页
     *
     * @return categories.ftl
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String categories() {
        return "categories";
    }

    /**
     * 一个分类页
     *
     * @param categoryId 分类id
     * @param pageNum 页数
     * @param request 可以取到contextPath和uri
     * @return category.ftl, 需要给出category posts
     */
    @RequestMapping(value = "/{categoryId:\\d+}/p/{page:\\d+}", method = RequestMethod.GET)
    public ModelAndView category(@PathVariable("categoryId") Integer categoryId, @PathVariable("page") Integer pageNum, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("category");
        String contextPath = request.getContextPath();
        String uri = request.getRequestURI();
        Category category = categoryService.getForeCategoryById(categoryId, contextPath);
        modelAndView.addObject("category", category);
        PageInfo<Post, Article> posts = articleService.getPostsByCategory(category, contextPath, uri, pageNum);
        modelAndView.addObject("posts", posts);
        return modelAndView;
    }

    @RequestMapping(value = "/{categoryId:\\d+}", method = RequestMethod.GET)
    public String categoryFirstPage(@PathVariable("categoryId") Integer categoryId) {
        return "redirect:/category/" + categoryId + "/p/1";
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
}
