package cn.quitomos.blog.controller.admin;

import cn.quitomos.blog.controller.base.BaseUploadFileController;
import cn.quitomos.blog.dto.ArticleDTO;
import cn.quitomos.blog.dto.LayuiJson;
import cn.quitomos.blog.dto.UploadFIleVO;
import cn.quitomos.blog.entity.Article;
import cn.quitomos.blog.entity.Category;
import cn.quitomos.blog.entity.Tag;
import cn.quitomos.blog.entity.User;
import cn.quitomos.blog.service.ArticleService;
import cn.quitomos.blog.service.CategoryService;
import cn.quitomos.blog.service.TagService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 后台文章相关
 */
@Controller
@RequestMapping("/admin/article")
public class BackArticleController extends BaseUploadFileController {

    private CategoryService categoryService;
    private TagService tagService;
    private ArticleService articleService;

    public BackArticleController() {
        super("resource/assets/img/article/", ".bmp.jpg.jpeg.png.gif");
    }

    /**
     * 写文章页
     *
     * @param model 需要给出categoryList, tagList
     * @return insert.jsp
     */
    @RequestMapping("/insert")
    public String insert(Model model) {
        List<Category> categoryList = categoryService.list();
        model.addAttribute("categoryList", categoryList);
        List<Tag> tagList = tagService.list();
        model.addAttribute("tagList", tagList);
        return ("Admin/Article/insert");
    }

    /**
     * 文章内容图片上传
     *
     * @param file 上传的文件
     * @param request request
     * @return json:["code": 0/1(成功/失败), "msg": 提示信息, "data.src": 文件访问uri]
     */
    @RequestMapping("/contentimg")
    @ResponseBody
    public LayuiJson uploadContentImg(MultipartFile file, HttpServletRequest request) {
        String name = super.save(file);
        String uri = request.getContextPath() + "/img/article/" + name;

        if (name == null) {
            return new LayuiJson(LayuiJson.Result.FAIL, "上传失败", null, 0);
        }
        UploadFIleVO uploadFIleVO = new UploadFIleVO(name, uri);
        return new LayuiJson<>(LayuiJson.Result.SUCCESS, "上传成功", uploadFIleVO, 1);
    }

    /**
     * 上传文章头图
     *
     * @param file 图片
     * @return json: ["code": 0/1(上传成功/失败), "data.title": 文件名, "msg": 提示信息]
     */
    @RequestMapping("/titleimg")
    @ResponseBody
    public LayuiJson uploadTitleImg(MultipartFile file) {
        String name = super.save(file);

        if (name == null) {
            return new LayuiJson(LayuiJson.Result.FAIL, "不支持的文件类型", null, 0);
        }
        UploadFIleVO uploadFIleVO = new UploadFIleVO(name, null);
        return new LayuiJson(LayuiJson.Result.SUCCESS, "上传成功", uploadFIleVO, 1);
    }

    /**
     * 新文章提交
     *
     * @param articleDTO articleDTO
     * @param session 获得user
     * @return /list
     */
    @RequestMapping("/insertSubmit")
    public String insertSubmit(ArticleDTO articleDTO, HttpSession session) {
        User user = (User) session.getAttribute("backUser");
        articleService.saveArticle(articleDTO, user);
        return "redirect:/admin/article/list";
    }

    /**
     *  文章列表
     *
     * @param model 需放入属性: {pageUrlPrefix: 为页码添加超链接 href="${pageUrlPrefix}=pageNum"
     *                          pageInfo: pageHelper返回值
     * @param request 可能含属性: pageNum: 页码  pageSize: 页大小
     * @return index.jsp
     */
    @RequestMapping("/list")
    public String list(Model model, HttpServletRequest request) {
        String pageNumStr = request.getParameter("pageNum");
        Integer pageNum = 1;
        if (pageNumStr != null)
            pageNum = Integer.parseInt(pageNumStr);
        String pageSizeStr = request.getParameter("pageSize");
        Integer pageSize = 10;
        if (pageSizeStr != null)
            pageSize = Integer.parseInt(pageSizeStr);
        PageInfo<Article> pageInfo = articleService.listArticlePaged(pageNum, pageSize, null);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("pageUrlPrefix", request.getContextPath() + "/admin/article/list?pageNum");
        return "Admin/Article/index";
    }

    /**
     * 删除文章
     *
     * @param id 文章id
     */
    @RequestMapping("/delete")
    public void delete(@RequestParam("articleid") Integer id) {
        articleService.deleteArticle(id);
    }

    /**
     * 修改文章页
     *
     * @param model 需要给出article, tagList, categoryList
     * @param id 文章id
     * @return edit.jsp
     */
    @RequestMapping("/edit")
    public String edit(@RequestParam("articleid") Integer id, Model model) {
        List<Category> categoryList = categoryService.list();
        model.addAttribute("categoryList", categoryList);
        List<Tag> tagList = tagService.list();
        model.addAttribute("tagList", tagList);
        Article article = articleService.getArticleById(id);
        model.addAttribute("article", article);
        return "Admin/Article/edit";
    }

    /**
     * 文章修改提交
     *
     * @param articleDTO articleDTO
     * @return /list
     */
    @RequestMapping("/editSubmit")
    public String editSubmit(ArticleDTO articleDTO) {
        articleService.saveArticle(articleDTO, null);
        return "redirect:/admin/article/list";
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
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
