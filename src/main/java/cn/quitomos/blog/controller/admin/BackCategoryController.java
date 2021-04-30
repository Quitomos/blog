package cn.quitomos.blog.controller.admin;

import cn.quitomos.blog.dto.CategoryDTO;
import cn.quitomos.blog.entity.Category;
import cn.quitomos.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 后台文章分类相关
 */
@Controller
@RequestMapping("/admin/category")
public class BackCategoryController {

    private CategoryService categoryService;

    /**
     * 文章分类首页
     *
     * @param model 需给出categoryList
     * @return index.jsp
     */
    @RequestMapping("")
    public String category(Model model) {
        List<Category> categoryList = categoryService.list();
        model.addAttribute("categoryList", categoryList);
        return "Admin/Category/index";
    }

    /**
     * 添加分类提交
     *
     * @param categoryDTO categoryDTO
     * @return /category
     */
    @RequestMapping("/insertSubmit")
    public String insertSubmit(CategoryDTO categoryDTO) {
        categoryService.saveCategory(categoryDTO);
        return "redirect:/admin/category";
    }

    /**
     * 删除分类
     *
     * @param id categoryId
     * @return /category
     */
    @RequestMapping("/delete")
    public String deleteCategory(@RequestParam("categoryid") Integer id) {
        categoryService.deleteCategory(id);
        return "redirect:/admin/category";
    }

    /**
     * 修改分类
     *
     * @param id categoryId
     * @param model 需要提供原category, categoryList
     * @return edit.jsp
     */
    @RequestMapping("/edit")
    public String editCategory(@RequestParam("categoryid") Integer id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        List<Category> categoryList = categoryService.list();
        model.addAttribute("categoryList", categoryList);
        return "Admin/Category/edit";
    }

    /**
     * 提交修改
     *
     * @param categoryDTO categoryDTO
     * @return /category
     */
    @RequestMapping("/editSubmit")
    public String editSubmit(CategoryDTO categoryDTO) {
        categoryService.saveCategory(categoryDTO);
        return "redirect:/admin/category";
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
