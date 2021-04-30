package cn.quitomos.blog.mapper;

import cn.quitomos.blog.BaseTest;
import cn.quitomos.blog.entity.Comment;
import cn.quitomos.blog.service.CommentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentMapperTest extends BaseTest {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentMapper commentMapper;

    @Test
    public void comment() {
//        Comment c = commentService.getCommentById(1);
    }
}
