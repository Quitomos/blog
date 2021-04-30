package cn.quitomos.blog.mapper;

import cn.quitomos.blog.entity.Article;
import cn.quitomos.blog.entity.User;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ArticleMapper {

    /**
     * 查询文章列表
     *
     * @param params 参数
     * @return 文章列表
     */
    List<Article> list(Map<String, Object> params);


    /**
     * 通过articleId获取文章
     *
     * @param id articleId
     * @return Article
     */
    Article getArticleById(int id);

    /**
     * 创建新文章
     *
     * @param article article
     */
    void insertArticle(Article article);

    /**
     * 删除文章
     *
     * @param id articleId
     */
    void deleteArticleById(int id);

    /**
     * 根据文章id更新文章
     *
     * @param article 文章
     */
    void updateArticle(Article article);

    /**
     * 获取早于date时间的第一篇文章
     *
     * @param date 给定时间
     * @return 文章
     */
    Article getPrevious(Date date);

    /**
     * 获取晚于date时间的第一篇文章
     *
     * @param date 给定时间
     * @return 文章
     */
    Article getNext(Date date);

    /**
     * 增加文章访问量
     *
     * @param id 文章id
     * @param add 增加量
     */
    void addViewCount(@Param("id") int id, @Param("add") int add);
}
