package cn.quitomos.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 全局选项配置
 */
@Data
public class Option implements Serializable {

    private static final long serialVersionUID = -1157740317666888756L;

    private Integer optionId;

    private String optionSiteTitle;

    private String optionSiteIcon;

    private String optionMetaDescription;

    private String optionMetaHello;

    private Integer optionViews;

    private Integer optionHostId;

    private String optionAbout;
}
