package cn.quitomos.blog.dto;

import cn.quitomos.blog.entity.Article;
import lombok.Data;

import java.util.List;

/**
 * 前台文章传入模型
 */
@Data
public class ArticleDTO {

    private Integer articleId;

    private String articleTitle;

    private String articleContent;

    private Integer articleParentCategoryId;

    private Integer articleChildCategoryId;

    private List<Integer> articleTagIds;

    private Integer articleStatus;

    private String articleImage;

    private String articleSummary;

    private Integer articleOrder;

}
