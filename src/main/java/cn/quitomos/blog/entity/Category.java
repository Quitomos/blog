package cn.quitomos.blog.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 文章分类
 */
@Data
public class Category implements Serializable {

    private static final long serialVersionUID = -3741620337625211921L;

    private Integer categoryId;

    @ToString.Exclude
    private Category parentCategory;

    private String categoryName;

    private String categoryDescription;

    private Integer categoryOrder;

    @ToString.Exclude
    private List<Article> articleList;

    private String categoryIcon;

    private Integer articleCount;

    @ToString.Exclude
    private List<Category> childList;
}
