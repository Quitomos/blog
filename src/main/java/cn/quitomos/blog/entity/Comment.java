package cn.quitomos.blog.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 文章评论
 */
@Data
public class Comment implements Serializable {

    private static final long serialVersionUID = 1530535241814902798L;

    private Integer commentId;

    //为了避免多级评论产生阶梯状排版，这里最好指向根评论
    private Comment parentComment;

    private Article article;

    private String commentContent;

    private String commentAgent;

    private String commentIp;

    private Date commentCreateTime;

    private String commentOs;

    private String commentName;

    private String commentAvatar;

    private String commentHomepage;

    private String commentEmail;

    @ToString.Exclude
    private List<Comment> childList;
}
