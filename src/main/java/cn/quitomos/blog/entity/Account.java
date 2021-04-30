package cn.quitomos.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 博客主的其他平台账户的链接信息
 */
@Data
public class Account implements Serializable {

    private static final long serialVersionUID = -6024069203895717488L;

    private Integer accountId;

    private String accountUrl;

    private String accountName;

    private String accountIcon;

    private Integer accountOrder;
}
