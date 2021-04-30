package cn.quitomos.blog.vo;

import cn.quitomos.blog.adapter.interf.Post;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Archive {

    public Archive() {
        posts = new ArrayList<>();
    }

    private Integer year;

    private Integer month;

    private List<Post> posts;
}
