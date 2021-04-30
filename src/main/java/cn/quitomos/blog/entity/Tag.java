package cn.quitomos.blog.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 文章标签
 */
@Data
public class Tag implements Serializable {

    private static final long serialVersionUID = 5488975319786826870L;

    private Integer tagId;

    private String tagName;

    private String tagDescription;

    @ToString.Exclude
    private List<Article> articleList;

    private Integer articleCount;
}
