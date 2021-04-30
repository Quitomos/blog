package cn.quitomos.blog.service.impl;

import cn.quitomos.blog.adapter.TagAdapter;
import cn.quitomos.blog.entity.Tag;
import cn.quitomos.blog.mapper.TagMapper;
import cn.quitomos.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private TagMapper tagMapper;

    @Override
    public List<Tag> list() {
        return tagMapper.list();
    }

    @Override
    public List<cn.quitomos.blog.adapter.interf.Tag> listFore(String contextPath) {
        List<Tag> tagEntityList = tagMapper.list();
        List<cn.quitomos.blog.adapter.interf.Tag> ret = new ArrayList<>();
        for (Tag t: tagEntityList) {
            ret.add(new TagAdapter(t, contextPath));
        }
        return ret;
    }

    @Override
    public void saveTag(Tag tag) {
        if (tag.getTagId() == null) {
            tagMapper.insertTag(tag);
        } else {
            tagMapper.updateTag(tag);
        }
    }

    @Override
    public void deleteTag(Integer id) {
        if (tagMapper.getTagById(id).getArticleCount() == 0)
            tagMapper.deleteTagById(id);
    }

    @Override
    public Tag getTagById(Integer id) {
        return tagMapper.getTagById(id);
    }

    @Override
    public cn.quitomos.blog.adapter.interf.Tag getForeTagById(Integer tagId, String contextPath) {
        return new TagAdapter(tagMapper.getTagById(tagId), contextPath);
    }

    @Autowired
    public void setTagMapper(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }
}
