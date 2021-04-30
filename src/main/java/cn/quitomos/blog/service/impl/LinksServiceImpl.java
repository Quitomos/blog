package cn.quitomos.blog.service.impl;

import cn.quitomos.blog.adapter.TeamAdapter;
import cn.quitomos.blog.adapter.interf.Team;
import cn.quitomos.blog.entity.Links;
import cn.quitomos.blog.mapper.LinksMapper;
import cn.quitomos.blog.service.LinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class LinksServiceImpl implements LinksService {

    private LinksMapper linksMapper;

    @Override
    public List<Links> list() {
        return linksMapper.list();
    }

    @Override
    public void saveLinks(Links links) {
        if (links.getLinksId() == null) {
            linksMapper.insertLinks(links);
        } else {
            linksMapper.updateLinks(links);
        }
    }

    @Override
    public void deleteLinks(Integer id) {
        linksMapper.deleteLinksById(id);
    }

    @Override
    public Links getLinksById(Integer id) {
        return linksMapper.getLinksById(id);
    }

    @Override
    public Team listFore(String contextPath) {
        List<Links> linksList = linksMapper.list();
        return new TeamAdapter(linksList, contextPath);
    }

    @Autowired
    public void setLinksMapper(LinksMapper linksMapper) {
        this.linksMapper = linksMapper;
    }
}
