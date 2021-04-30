package cn.quitomos.blog.adapter;

import cn.quitomos.blog.adapter.interf.Tag;

import java.io.Serializable;

public class TagAdapter implements Tag, Serializable {

    private static final long serialVersionUID = 1648077129629758090L;
    private final cn.quitomos.blog.entity.Tag tag;
    private final String contextPath;

    public TagAdapter(cn.quitomos.blog.entity.Tag tag, String contextPath) {
        this.tag = tag;
        this.contextPath = contextPath;
    }

    @Override
    public String getName() {
        return tag.getTagName();
    }

    @Override
    public int getPostCount() {
        return tag.getArticleCount();
    }

    @Override
    public String getFullPath() {
        return contextPath + "/tag/" + tag.getTagId();
    }

    @Override
    public String getThumbnail() {
        return contextPath + "/img/tag_patternimg.jpg";
    }

    @Override
    public String getDescription() {
        return tag.getTagDescription();
    }

    @Override
    public cn.quitomos.blog.entity.Tag getSource() {
        return this.tag;
    }
}
