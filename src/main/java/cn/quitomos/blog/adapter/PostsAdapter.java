package cn.quitomos.blog.adapter;

import cn.quitomos.blog.adapter.base.PageInfoAdapter;
import cn.quitomos.blog.adapter.interf.Post;
import cn.quitomos.blog.entity.Article;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PostsAdapter extends PageInfoAdapter<Post, Article> implements Serializable {

    private static final long serialVersionUID = 4104185023199874408L;
    private final String contextPath;


    public PostsAdapter(PageInfo<Article> pageInfo, String uri, String contextPath) {
        super(pageInfo, uri);
        this.contextPath = contextPath;
    }

    @Override
    protected List<Post> typeChange(List<Article> v) {
        List<Post> posts = new ArrayList<>();
        for (Article a: v) {
            posts.add(new PostAdapter(a, contextPath));
        }
        return posts;
    }
}
