package cn.quitomos.blog.service;

import cn.quitomos.blog.adapter.interf.Team;
import cn.quitomos.blog.entity.Links;

import java.util.List;

public interface LinksService {

    /**
     * 友链列表
     * @return linksList
     */
    List<Links> list();

    /**
     * 添加/修改友链
     *
     * @param links links
     */
    void saveLinks(Links links);

    /**
     * 删除友链
     *
     * @param id linksId
     */
    void deleteLinks(Integer id);

    /**
     * 根据友链id获取友链
     *
     * @param id linksId
     * @return
     */
    Links getLinksById(Integer id);

    /**
     * 前台友链列表
     *
     * @param contextPath 上下文
     * @return team
     */
    Team listFore(String contextPath);
}
