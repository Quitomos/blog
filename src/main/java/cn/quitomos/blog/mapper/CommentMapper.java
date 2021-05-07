package cn.quitomos.blog.mapper;

import cn.quitomos.blog.entity.Article;
import cn.quitomos.blog.entity.Comment;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public interface CommentMapper {

    /**
     * 获取评论列表
     *
     * @param params 参数
     * @return commentList
     */
    List<Comment> list(Map<String, Object> params);

    /**
     * 通过commentId获取评论
     *
     * @param id commentId
     * @return Comment
     */
    Comment getCommentById(int id);

    /**
     * 删除某文章的评论
     *
     * @param id articleId
     */
    void deleteCommentByArticleId(@Param("article_id") int id);

    /**
     * 根据id删除评论
     *
     * @param id commentId
     */
    void deleteCommentById(@Param("comment_id") int id);

    /**
     * 插入评论
     *
     * @param comment comment
     */
    void insertComment(Comment comment);

    /**
     * 获取某文章的评论数量
     *
     * @param id articleId
     * @return count
     */
    Integer getCountByArticleId(int id);

    /**
     * 只实现了更新评论是否发布
     *
     * @param comment comment
     */
    void updateComment(Comment comment);
}
