package cn.quitomos.blog.adapter;

import cn.quitomos.blog.adapter.base.PageInfoAdapter;
import cn.quitomos.blog.adapter.interf.Comment;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;

public class CommentsAdapter extends PageInfoAdapter<Comment, cn.quitomos.blog.entity.Comment> {

    private final String adminAvatar;
    private final String adminEmail;

    public CommentsAdapter(PageInfo<cn.quitomos.blog.entity.Comment> pageInfo, String uri, String adminAvatar, String adminEmail) {
        super(pageInfo, uri);
        this.adminAvatar = adminAvatar;
        this.adminEmail = adminEmail;
    }

    @Override
    protected List<Comment> typeChange(List<cn.quitomos.blog.entity.Comment> v) {
        List<Comment> ret = new ArrayList<>();
        for (cn.quitomos.blog.entity.Comment c: v) {
            ret.add(new CommentAdapter(c, adminAvatar, adminEmail));
        }
        return ret;
    }

    public int getCommentCount() {
        List<Comment> comments = super.getContent();
        int ret = comments.size();
        for (Comment c: comments) {
            if (c.getChildren() != null && !c.getChildren().isEmpty()) {
                ret += c.getChildren().size();
            }
        }
        return ret;
    }
}
