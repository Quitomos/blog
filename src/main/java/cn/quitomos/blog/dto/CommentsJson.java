package cn.quitomos.blog.dto;

import cn.quitomos.blog.adapter.CommentsAdapter;
import lombok.Data;

@Data
public class CommentsJson {

    private Object data;

    public CommentsJson(Object commentsAdapter) {
        this.data = commentsAdapter;
    }
}
