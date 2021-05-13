package cn.quitomos.blog.vo;

import lombok.Data;

@Data
public class Sheet {

    private String title;

    private String thumbnail;

    private Integer id;

    private Integer commentCount;

    private boolean disallowComment;

    public Sheet(String contextPath) {
        this.id = 0;
        this.disallowComment = false;
        this.thumbnail = contextPath + "/img/message_patternimg.jpg";
        this.title = "一句话";
    }
}
