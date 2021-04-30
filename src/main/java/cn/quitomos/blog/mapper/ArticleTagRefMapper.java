package cn.quitomos.blog.mapper;

import cn.quitomos.blog.entity.Article;
import cn.quitomos.blog.entity.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleTagRefMapper {

    /**
     * 查询某个标签下的所有文章
     *
     * @param id tagId
     * @return articleList
     */
    List<Article> getArticleListByTagId(@Param("tag_id") int id);

    /**
     * 查询某篇文章的所有标签
     *
     * @param id articleId
     * @return tagList
     */
    List<Tag> getTagListByArticleId(@Param("article_id") int id);

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
     * @param tagId tagId
     */
    void insertRef(@Param("article_id") int articleId, @Param("tag_id") int tagId);

    /**
     * 获得标签下的文章总数
     *
     * @param tagId 标签ID
     * @return 文章总数
     */
    Integer getTotalArticleByTagId(@Param("tag_id") int tagId);
}
