package cn.quitomos.blog.entity;

import cn.quitomos.blog.enums.ArticleStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 博客文章
 */
@Data
public class Article implements Serializable {

    private static final long serialVersionUID = -6298840579010424967L;

    private Integer articleId;

    //设定为所有管理员都可以以自己的名义发文章
    private User user;

    private String articleTitle;

    private String articleContent;

    private Integer articleViewCount;

    private Integer articleCommentCount;

    private Integer articleLikeCount;

    @Getter(value = AccessLevel.NONE)
    private Boolean articleIsComment;

    private ArticleStatus articleStatus;

    private Integer articleOrder;

    private Date articleUpdateTime;

    private Date articleCreateTime;

    private String articleSummary;

    private String articleImage;

    @ToString.Exclude
    private List<Category> categoryList;

    @ToString.Exclude
    private List<Tag> tagList;

    public Boolean isArticleIsComment() {
        return this.articleIsComment;
    }
}
