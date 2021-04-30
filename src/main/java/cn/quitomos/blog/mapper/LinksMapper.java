package cn.quitomos.blog.mapper;

import cn.quitomos.blog.entity.Links;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LinksMapper {

    /**
     * 友链列表
     *
     * @return linksList
     */
    List<Links> list();

    /**
     * 通过linksId获得友链
     *
     * @param id linksId
     * @return links
     */
    Links getLinksById(int id);

    /**
     * 添加友链
     *
     * @param links 添加的友链
     */
    void insertLinks(Links links);

    /**
     * 根据linksId修改
     *
     * @param links 修改的友链
     */
    void updateLinks(Links links);

    /**
     * 根据linksId删除友链
     *
     * @param id categoryId
     */
    void deleteLinksById(@Param("links_id") int id);
}
