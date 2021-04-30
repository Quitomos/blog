package cn.quitomos.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 友链
 */
@Data
public class Links implements Serializable {

    private static final long serialVersionUID = -365663228315506478L;

    private Integer linksId;

    private String linksUrl;

    private String linksName;

    private String linksImage;

    private String linksDescription;

    private Integer linksOrder;
}
