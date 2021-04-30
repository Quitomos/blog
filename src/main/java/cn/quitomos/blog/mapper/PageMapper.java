package cn.quitomos.blog.mapper;

import cn.quitomos.blog.entity.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PageMapper {

    /**
     * 页面列表
     *
     * @return pageList
     */
    List<Page> list();

    /**
     * 通过pageId获得页面
     *
     * @param id pageId
     * @return page
     */
    Page getPageById(int id);

    /**
     * 添加页面
     *
     * @param page 添加的页面
     */
    void insertPage(Page page);

    /**
     * 根据pageId修改
     *
     * @param page 修改的页面
     */
    void updatePage(Page page);

    /**
     * 根据pageId删除页面
     *
     * @param id categoryId
     */
    void deletePageById(@Param("page_id") int id);
}
