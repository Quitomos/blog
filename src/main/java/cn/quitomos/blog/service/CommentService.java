package cn.quitomos.blog.service;

import cn.quitomos.blog.adapter.CommentsAdapter;
import cn.quitomos.blog.dto.BackReplyDTO;
import cn.quitomos.blog.dto.CommentDTO;
import cn.quitomos.blog.entity.Comment;
import cn.quitomos.blog.vo.Sheet;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface CommentService {

    /**
     * 获取最近未审核评论
     *
     * @param num 评论条数
     * @return 评论列表
     */
    List<Comment> getRecentPendingComments(Integer num);

    /**
     * 评论列表
     * 
     * @param pageNum 页码
     * @param pageSize 每页行数
     * @param params 查询参数
     * @return 评论列表
     */
    PageInfo<Comment> listCommentPaged(Integer pageNum, Integer pageSize, Map<String ,Object> params);

    /**
     * 前台某篇文章的评论
     *
     * @param articleId 文章id
     * @param pageNum 页数
     * @param uri uri
     * @param contextPath 上下文
     * @return comments
     */
    CommentsAdapter listForeRootCommentPaged(int articleId, int pageNum, String uri, String contextPath);

    /**
     * 删除评论
     *
     * @param commentId 评论id
     */
    void deleteComment(Integer commentId);

    /**
     * 根据评论id获取评论
     *
     * @param commentId 评论id
     * @return comment
     */
    Comment getCommentById(Integer commentId);

    /**
     * 新增评论回复
     *
     * @param backReplyDTO 后台前端传入回复
     * @param request 用来提取ip、浏览器信息
     */
    void insertCommentReply(BackReplyDTO backReplyDTO, HttpServletRequest request);

    /**
     * 发布评论
     * @param commentId
     */
    void publishComment(Integer commentId);

    /**
     * 留言板
     *
     * @param contextPath 上下文
     * @return sheet
     */
    Sheet getSheet(String contextPath);

    /**
     * 新评论
     *
     * @param commentDTO 新评论模型
     * @param request 提交时request
     * @return commentAdapter
     */
    cn.quitomos.blog.adapter.interf.Comment addComment(CommentDTO commentDTO, HttpServletRequest request);
}
