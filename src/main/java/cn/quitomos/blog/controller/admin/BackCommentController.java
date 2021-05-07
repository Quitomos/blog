package cn.quitomos.blog.controller.admin;

import cn.quitomos.blog.dto.BackReplyDTO;
import cn.quitomos.blog.entity.Comment;
import cn.quitomos.blog.service.CommentService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 后台评论相关
 */
@Controller
@RequestMapping("admin/comment")
public class BackCommentController {

    private CommentService commentService;

    /**
     *  评论列表
     *
     * @param model 需放入属性: {pageUrlPrefix: 为页码添加超链接 href="${pageUrlPrefix}=pageNum"
     *                          pageInfo: pageHelper返回值
     * @param request 可能含属性: pageNum: 页码  pageSize: 页大小
     * @return index.jsp
     */
    @RequestMapping("")
    public String list(Model model, HttpServletRequest request) {
        String pageNumStr = request.getParameter("pageNum");
        Integer pageNum = 1;
        if (pageNumStr != null)
            pageNum = Integer.parseInt(pageNumStr);
        String pageSizeStr = request.getParameter("pageSize");
        Integer pageSize = 10;
        if (pageSizeStr != null)
            pageSize = Integer.parseInt(pageSizeStr);
        PageInfo<Comment> pageInfo = commentService.listCommentPaged(pageNum, pageSize, null);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("pageUrlPrefix", request.getContextPath() + "/admin/comment?pageNum");
        return "Admin/Comment/index";
    }

    /**
     * 评论回复页
     *
     * @param model 需存入回复的comment
     * @param commentId commentId
     * @return reply.jsp
     */
    @RequestMapping("/reply")
    public String reply(Model model, @RequestParam("commentid") Integer commentId) {
        Comment comment = commentService.getCommentById(commentId);
        model.addAttribute("comment", comment);
        return "Admin/Comment/reply";
    }

    /**
     * 删除评论
     *
     * @param commentId 要删除的评论id
     */
    @RequestMapping("/delete")
    public void delete(@RequestParam("commentid") Integer commentId) {
        commentService.deleteComment(commentId);
    }

    /**
     * 回复评论提交
     *
     * @param backReplyDTO 回复评论传入模型
     * @param request 获得ip、浏览器信息
     * @return ""
     */
    @RequestMapping("/replySubmit")
    public String replySubmit(BackReplyDTO backReplyDTO, HttpServletRequest request) {
        commentService.insertCommentReply(backReplyDTO, request);
        return "redirect:/admin/comment";
    }

    @RequestMapping("/published")
    public String published(@RequestParam("commentid") Integer commentId, @RequestParam("p") String page) {
        commentService.publishComment(commentId);
        if ("index".equals(page)) {
            return "redirect:/admin";
        }
        return "redirect:/admin/comment";
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }
}
