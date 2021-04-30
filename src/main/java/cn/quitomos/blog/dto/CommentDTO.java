package cn.quitomos.blog.dto;

import lombok.Data;

/**
 * 前台评论传入模型
 */
@Data
public class CommentDTO {

    private boolean allowNotification;

    private String author;

    private String authorUrl;

    private String content;

    private Integer parentId;

    private Integer postId;

    private String email;

    private String avatar;
}
