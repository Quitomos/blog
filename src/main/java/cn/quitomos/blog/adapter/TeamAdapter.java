package cn.quitomos.blog.adapter;

import cn.quitomos.blog.adapter.interf.Links;
import cn.quitomos.blog.adapter.interf.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamAdapter implements Team {

    private final List<cn.quitomos.blog.entity.Links> links;
    private final String contextPath;

    public TeamAdapter(List<cn.quitomos.blog.entity.Links> links, String contextPath) {
        this.links = links;
        this.contextPath = contextPath;
    }

    @Override
    public String getTeam() {
        return "大佬们";
    }

    @Override
    public List<Links> getLinks() {
        List<Links> ret = new ArrayList<>();
        for (cn.quitomos.blog.entity.Links l: links) {
            ret.add(new LinksAdapter(l, contextPath));
        }
        return ret;
    }
}
