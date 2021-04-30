package cn.quitomos.blog.service.impl;

import cn.quitomos.blog.entity.Page;
import cn.quitomos.blog.mapper.PageMapper;
import cn.quitomos.blog.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PageServiceImpl implements PageService {

    private PageMapper pageMapper;

    @Override
    public List<Page> list() {
        return pageMapper.list();
    }

    @Override
    public void savePage(Page page) {
        if (page.getPageId() == null) {
            pageMapper.insertPage(page);
        } else {
            pageMapper.updatePage(page);
        }
    }

    @Override
    public void deletePage(Integer id) {
        pageMapper.deletePageById(id);
    }

    @Override
    public Page getPageById(Integer id) {
        return pageMapper.getPageById(id);
    }

    @Autowired
    public void setPageMapper(PageMapper pageMapper) {
        this.pageMapper = pageMapper;
    }
}