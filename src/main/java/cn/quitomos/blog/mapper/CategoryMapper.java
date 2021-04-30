package cn.quitomos.blog.mapper;

import cn.quitomos.blog.entity.Category;
import cn.quitomos.blog.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CategoryMapper {

    /**
     * 文章分类列表
     *
     * @param params 参数
     * @return categoryList
     */
    List<Category> list(Map<String, Object> params);

    /**
     * 子分类列表
     *
     * @param id 父导航id
     * @return childList
     */
    List<Category> listChild(int id);

    /**
     * 通过categoryId获得文章分类
     *
     * @param id categoryId
     * @return category
     */
    Category getCategoryById(int id);

    /**
     * 添加分类
     *
     * @param category 添加的分类
     */
    void insertCategory(Category category);

    /**
     * 根据categoryId修改分类
     *
     * @param category 修改的分类
     */
    void updateCategory(Category category);

    /**
     * 根据categoryId删除分类
     *
     * @param id categoryId;
     */
    void deleteCategoryById(@Param("category_id") int id);
}
