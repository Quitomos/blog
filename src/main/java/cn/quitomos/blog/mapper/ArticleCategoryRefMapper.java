package cn.quitomos.blog.mapper;

import cn.quitomos.blog.entity.Article;
import cn.quitomos.blog.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleCategoryRefMapper {

    /**
     * 查询某个分类下的所有文章
     *
     * @param id categoryId
     * @return articleList
     */
    List<Article> getArticleListByCategoryId(@Param("category_id") int id);

    /**
     * 查询某篇文章属于的所有分类
     *
     * @param id articleId
     * @return categoryList
     */
    List<Category> getCategoryListByArticleId(@Param("article_id") int id);

    /**
     * 根据文章id删除关系
     *
     * @param id articleId
     */
    void deleteRefByArticleId(@Param("article_id") int id);

    /**
     * 添加新的关系
     *
     * @param articleId articleId
     * @param categoryId categoryId
     */
    void insertRef(@Param("article_id") int articleId, @Param("category_id") int categoryId);

    /**
     * 获得分类下的文章总数
     *
     * @param categoryId 分类ID
     * @return 文章总数
     */
    Integer getTotalArticleByCategoryId(@Param("category_id") int categoryId);
}
