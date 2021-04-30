package cn.quitomos.blog.service.impl;

import cn.quitomos.blog.adapter.CommentAdapter;
import cn.quitomos.blog.adapter.CommentsAdapter;
import cn.quitomos.blog.dto.BackReplyDTO;
import cn.quitomos.blog.dto.CommentDTO;
import cn.quitomos.blog.entity.Article;
import cn.quitomos.blog.entity.Comment;
import cn.quitomos.blog.entity.User;
import cn.quitomos.blog.mapper.CommentMapper;
import cn.quitomos.blog.service.ArticleService;
import cn.quitomos.blog.service.CommentService;
import cn.quitomos.blog.service.OptionService;
import cn.quitomos.blog.service.UserService;
import cn.quitomos.blog.util.HtmlUtil;
import cn.quitomos.blog.util.IPUtil;
import cn.quitomos.blog.util.Md5Util;
import cn.quitomos.blog.vo.Sheet;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentMapper commentMapper;
    private UserService userService;
    private OptionService optionService;
    private ArticleService articleService;

    @Override
    public List<Comment> getRecentComments(Integer num) {
        Map<String, Object> params = new HashMap<>();
        params.put("commentOrder", "comment_create_time desc");
        PageHelper.startPage(1, num);
        return commentMapper.list(params);
    }

    @Override
    public PageInfo<Comment> listCommentPaged(Integer pageNum, Integer pageSize, Map<String, Object> params) {
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> commentList = commentMapper.list(params);
        return new PageInfo<>(commentList);
    }

    @Override
    public CommentsAdapter listForeRootCommentPaged(int articleId, int pageNum, String uri, String contextPath) {
        PageHelper.startPage(pageNum, 10);
        Map<String, Object> params = new HashMap<>();
        params.put("articleId", articleId);
        params.put("commentPid", 0);
        List<Comment> commentList = commentMapper.list(params);
        PageInfo<Comment> pageInfo = new PageInfo<>(commentList);
        User admin = userService.getUserById(optionService.getOption().getOptionHostId());
        return new CommentsAdapter(pageInfo, uri, contextPath + "/img/avatar" + admin.getUserAvatar(), admin.getUserEmail());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteComment(Integer commentId) {
        Map<String, Object> params = new HashMap<>();
        params.put("commentPid", commentId);
        List<Comment> childComment = commentMapper.list(params);
        // 递归删除子评论
        for (Comment c: childComment)
            deleteComment(c.getCommentId());
        commentMapper.deleteCommentById(commentId);
    }

    @Override
    public Comment getCommentById(Integer commentId) {
        return commentMapper.getCommentById(commentId);
    }

    @Override
    public void insertCommentReply(BackReplyDTO backReplyDTO, HttpServletRequest request) {
        Comment comment = new Comment();
        Comment parentComment = commentMapper.getCommentById(backReplyDTO.getCommentPid());

        // 父评论为根评论，保证评论最多只有两级
        while (parentComment.getParentComment() != null)
            parentComment = commentMapper.getCommentById(parentComment.getParentComment().getCommentId());
        comment.setParentComment(parentComment);

        comment.setArticle(parentComment.getArticle());

        String ip = IPUtil.getIPAddress(request);
        comment.setCommentIp(ip);

        String browser = HtmlUtil.getBrowserInfo(request);
        comment.setCommentAgent(browser);

        comment.setCommentCreateTime(new Date());
        User user = (User) request.getSession().getAttribute("backUser");
        comment.setCommentName(user.getUserNickname());
        comment.setCommentAvatar(request.getContextPath() + "/img/avatar/" + user.getUserAvatar());
        comment.setCommentOs(HtmlUtil.getOsInfo(request));
        comment.setCommentHomepage(request.getContextPath());
        comment.setCommentEmail(user.getUserEmail());
        comment.setCommentContent(backReplyDTO.getCommentContent());

        commentMapper.insertComment(comment);
    }

    @Override
    public Sheet getSheet(String contextPath) {
        Sheet sheet = new Sheet(contextPath);
        sheet.setCommentCount(commentMapper.getCountByArticleId(0));
        return sheet;
    }

    @Override
    public cn.quitomos.blog.adapter.interf.Comment addComment(CommentDTO commentDTO, HttpServletRequest request) {
        Comment comment = new Comment();
        int parentId = commentDTO.getParentId() == null? 0: commentDTO.getParentId();
        if (parentId != 0) {
            Comment parent = commentMapper.getCommentById(parentId);
            while (parent.getParentComment() != null)
                parent = parent.getParentComment();
            comment.setParentComment(parent);
        }
        int articleId = commentDTO.getPostId();
        if (articleId != 0) {
            Article article = articleService.getArticleById(articleId);
            comment.setArticle(article);
        }
        comment.setCommentContent(commentDTO.getContent());
        comment.setCommentAgent(HtmlUtil.getBrowserInfo(request));
        comment.setCommentIp(IPUtil.getIPAddress(request));
        comment.setCommentCreateTime(new Date());
        comment.setCommentOs(HtmlUtil.getOsInfo(request));
        comment.setCommentName(commentDTO.getAuthor());
        comment.setCommentAvatar(commentDTO.getAvatar());
        comment.setCommentHomepage(commentDTO.getAuthorUrl());
        comment.setCommentEmail(commentDTO.getEmail());

        commentMapper.insertComment(comment);

        User admin = userService.getUserById(optionService.getOption().getOptionHostId());
        return new CommentAdapter(comment, request.getContextPath() + "/img/avatar/" + admin.getUserAvatar(), admin.getUserEmail());
    }

    @Autowired
    public void setCommentMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOptionService(OptionService optionService) {
        this.optionService = optionService;
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
}
