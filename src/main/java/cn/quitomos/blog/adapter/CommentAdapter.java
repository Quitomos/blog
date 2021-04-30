package cn.quitomos.blog.adapter;

import cn.quitomos.blog.adapter.interf.Comment;
import cn.quitomos.blog.util.Md5Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentAdapter implements Comment {

    private final cn.quitomos.blog.entity.Comment comment;
    private final String adminAvatar;
    private final String adminEmail;

    public CommentAdapter(cn.quitomos.blog.entity.Comment comment, String adminAvatar, String adminEmail) {
        this.comment = comment;
        this.adminAvatar = adminAvatar;
        this.adminEmail = adminEmail;
    }

    @Override
    public boolean isAllowNotification() {
        return false;
    }

    @Override
    public List<Comment> getChildren() {
        List<Comment> ret = new ArrayList<>();
        List<cn.quitomos.blog.entity.Comment> commentEntityList = comment.getChildList();
        if (commentEntityList == null || commentEntityList.isEmpty())
            return ret;
        for (cn.quitomos.blog.entity.Comment c: commentEntityList) {
            ret.add(new CommentAdapter(c, adminAvatar, adminEmail));
        }
        return ret;
    }

    @Override
    public int getId() {
        return comment.getCommentId() == null? 0: comment.getCommentId();
    }

    @Override
    public String getAuthor() {
        return comment.getCommentName();
    }

    @Override
    public String getEmail() {
        return comment.getCommentEmail();
    }

    @Override
    public String getIpAddress() {
        return comment.getCommentIp();
    }

    @Override
    public String getAuthorUrl() {
        return comment.getCommentHomepage();
    }

    @Override
    public String getGravatarMd5() {
        return Md5Util.String2Md5(comment.getCommentEmail());
    }

    @Override
    public String getContent() {
        return comment.getCommentContent();
    }

    @Override
    public String getUserAgent() {
        return comment.getCommentAgent();
    }

    @Override
    public int getParentId() {
        if (comment.getParentComment() == null) return 0;
        return comment.getParentComment().getCommentId();
    }

    @Override
    public boolean isIsAdmin() {
        if (comment.getCommentEmail().equals(adminEmail))
            return true;
        return false;
    }

    @Override
    public Date getCreateTime() {
        return comment.getCommentCreateTime();
    }

    @Override
    public String getAvatar() {
        return comment.getCommentAvatar();
    }

    @Override
    public String getStatus() {
        return "PUBLISHED";
    }
}
