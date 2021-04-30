package cn.quitomos.blog.dto;

import lombok.Data;

/**
 * 后台前端回复评论传入模型
 */
@Data
public class BackReplyDTO {

    private Integer commentPid;

    private String commentContent;
}
