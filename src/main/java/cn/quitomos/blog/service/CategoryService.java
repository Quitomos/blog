package cn.quitomos.blog.service;

import cn.quitomos.blog.dto.CategoryDTO;
import cn.quitomos.blog.entity.Category;

import java.util.List;

public interface CategoryService {

    /**
     * 文章分类列表
     *
     * @return categoryList
     */
    List<Category> list();

    /**
     * 文章根分类列表
     *
     * @param contextPath 上下文
     * @return 适配前台的categories
     */
    List<cn.quitomos.blog.adapter.interf.Category> listRoot(String contextPath);

    /**
     * 添加/修改分类
     *
     * @param categoryDTO categoryDTO
     */
    void saveCategory(CategoryDTO categoryDTO);

    /**
     * 删除分类
     *
     * @param categoryId 分类Id
     */
    void deleteCategory(Integer categoryId);

    /**
     * 根据分类id获取分类
     *
     * @param categoryId categoryId
     * @return category
     */
    Category getCategoryById(Integer categoryId);

    /**
     * 根据分类id获取前台分类
     *
     * @param contextPath 上下文
     * @param categoryId categoryId
     * @return 适配前台的category
     */
    cn.quitomos.blog.adapter.interf.Category getForeCategoryById(Integer categoryId, String contextPath);
}
