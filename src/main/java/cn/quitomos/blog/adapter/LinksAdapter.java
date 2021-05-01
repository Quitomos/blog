package cn.quitomos.blog.adapter;

import cn.quitomos.blog.adapter.interf.Links;

public class LinksAdapter implements Links {

    private final cn.quitomos.blog.entity.Links links;
    private final String contextPath;

    public LinksAdapter(cn.quitomos.blog.entity.Links links, String contextPath) {
        this.links = links;
        this.contextPath = contextPath;
    }

    @Override
    public String getUrl() {
        return links.getLinksUrl();
    }

    @Override
    public String getName() {
        return links.getLinksName();
    }

    @Override
    public String getLogo() {
        return links.getLinksImage();
    }

    @Override
    public String getDescription() {
        return links.getLinksDescription();
    }
}
