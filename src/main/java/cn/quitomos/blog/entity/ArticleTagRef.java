package cn.quitomos.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 文章和标签的中间表
 */
@Data
public class ArticleTagRef implements Serializable {

    private static final long serialVersionUID = -5594100251441626946L;

    private Integer articleId;

    private Integer tagId;
}
