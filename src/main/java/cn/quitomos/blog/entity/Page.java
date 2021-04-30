package cn.quitomos.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 自定义页面
 */
@Data
public class Page implements Serializable {

    private static final long serialVersionUID = -7991209358589920059L;

    private Integer pageId;

    private String pageTitle;

    private String pageUrl;

    private String pageImage;

    private String pageDescription;

    private Integer pageOrder;

    private String pageTarget;
}
