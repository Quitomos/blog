package cn.quitomos.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 文章和分类的中间表
 */
@Data
public class ArticleCategoryRef implements Serializable {

    private static final long serialVersionUID = 964515243327778020L;

    private Integer articleId;

    private Integer categoryId;
}
