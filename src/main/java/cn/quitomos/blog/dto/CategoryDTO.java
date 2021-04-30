package cn.quitomos.blog.dto;

import lombok.Data;

/**
 * 前端分类传入模型
 */
@Data
public class CategoryDTO {

    private Integer categoryId;

    private String categoryName;

    private Integer categoryPid;

    private String categoryDescription;

    private Integer categoryOrder;

    private String categoryIcon;

}
