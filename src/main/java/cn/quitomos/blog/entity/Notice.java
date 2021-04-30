package cn.quitomos.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 公告
 */
@Data
public class Notice implements Serializable {

    private static final long serialVersionUID = 7237642441847154293L;

    private Integer noticeId;

    private String noticeTitle;

    private String noticeContent;

    private Date noticeCreateTime;

    private Date noticeUpdateTime;

    private Integer noticeOrder;
}
