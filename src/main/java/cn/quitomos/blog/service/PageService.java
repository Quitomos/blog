package cn.quitomos.blog.service;

import cn.quitomos.blog.entity.Page;

import java.util.List;

public interface PageService {

    /**
     * 页面列表
     * @return pageList
     */
    List<Page> list();

    /**
     * 添加/修改页面
     *
     * @param page page
     */
    void savePage(Page page);

    /**
     * 删除页面
     *
     * @param id pageId
     */
    void deletePage(Integer id);

    /**
     * 根据页面id获取页面
     *
     * @param id pageId
     * @return
     */
    Page getPageById(Integer id);
}
