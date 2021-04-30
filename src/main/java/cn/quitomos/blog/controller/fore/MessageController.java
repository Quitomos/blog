package cn.quitomos.blog.controller.fore;

import cn.quitomos.blog.entity.Comment;
import cn.quitomos.blog.mapper.CommentMapper;
import cn.quitomos.blog.service.CommentService;
import cn.quitomos.blog.vo.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/message")
public class MessageController {

    private CommentService commentService;

    /**
     * 留言板首页
     *
     * @param request 拿到contextPath
     * @return sheet.ftl, 需给出is_sheet sheet
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView message(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("sheet");
        String contextPath = request.getContextPath();
        Sheet sheet = commentService.getSheet(contextPath);
        modelAndView.addObject("is_sheet", true);
        modelAndView.addObject("sheet", sheet);
        return modelAndView;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }
}
