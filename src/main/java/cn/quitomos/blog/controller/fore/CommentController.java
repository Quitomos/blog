package cn.quitomos.blog.controller.fore;

import cn.quitomos.blog.adapter.CommentAdapter;
import cn.quitomos.blog.adapter.CommentsAdapter;
import cn.quitomos.blog.adapter.interf.Comment;
import cn.quitomos.blog.dto.CommentDTO;
import cn.quitomos.blog.dto.CommentsJson;
import cn.quitomos.blog.service.CommentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/content")
public class CommentController {

    private CommentService commentService;

    /**
     * 某文章的评论
     *
     * @param postId 文章id
     * @param page 页数
     * @param request request
     * @return comments
     */
    @RequestMapping(value = "/posts/{postId:\\d+}/comments/tree_view", method = RequestMethod.GET)
    public CommentsJson listCommentTree(@PathVariable("postId") Integer postId,
                                      @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                      HttpServletRequest request) {
        int pageNum = page + 1;
        CommentsAdapter ret = commentService.listForeRootCommentPaged(postId, pageNum, request.getRequestURI(), request.getContextPath());
        return new CommentsJson(ret);
    }

    /**
     * 新评论
     *
     * @param commentDTO
     * @param request
     * @return
     */
    @RequestMapping(value = "/posts/comments", method = RequestMethod.POST)
    public CommentsJson articleComment(@RequestBody CommentDTO commentDTO, HttpServletRequest request) {
        commentDTO.setContent(HtmlUtils.htmlEscape(
                commentDTO.getContent(), StandardCharsets.UTF_8.displayName()
        ));
        CommentAdapter ret = (CommentAdapter) commentService.addComment(commentDTO, request);
        return new CommentsJson(ret);
    }

    /**
     * 留言
     *
     * @param sheetId 留言板，默认0
     * @param page 页数
     * @param request request
     * @return comments
     */
    @RequestMapping(value = "/sheets/{sheetId:\\d+}/comments/tree_view", method = RequestMethod.GET)
    public CommentsJson listMessageTree(@PathVariable("sheetId") Integer sheetId,
                                        @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                        HttpServletRequest request) throws JsonProcessingException {
        int pageNum = page + 1;
        CommentsAdapter ret = commentService.listForeRootCommentPaged(sheetId, pageNum, request.getRequestURI(), request.getContextPath());
        return new CommentsJson(ret);
    }

    /**
     * 新留言
     *
     * @param commentDTO
     * @param request
     * @return
     */
    @RequestMapping(value = "/sheets/comments", method = RequestMethod.POST)
    public CommentsJson messageComment(@RequestBody CommentDTO commentDTO, HttpServletRequest request) {
        commentDTO.setContent(HtmlUtils.htmlEscape(
                commentDTO.getContent(), StandardCharsets.UTF_8.displayName()
        ));
        System.out.println(commentDTO);
        CommentAdapter ret = (CommentAdapter) commentService.addComment(commentDTO, request);
        return new CommentsJson(ret);
    }

    /**
     * 照葫芦画瓢的设置
     *
     * @return
     */
    @RequestMapping("/options/comment")
    public CommentsJson options() {
        Map<String, Object> options = new HashMap<>();
        options.put("comment_gravatar_default", "mm");
        options.put("comment_content_placeholder", "这个轮回，终于遇到你 ...");
        options.put("gravatar_source", "//cdn.v2ex.com/gravatar");
        return new CommentsJson(options);
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }
}
