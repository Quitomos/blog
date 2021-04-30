package cn.quitomos.blog.service;

import cn.quitomos.blog.entity.Tag;

import java.util.List;

public interface TagService {

    /**
     * 文章标签列表
     *
     * @return tagList
     */
    List<Tag> list();

    /**
     * 文章标签列表
     *
     * @return 适配前台后的tags
     */
    List<cn.quitomos.blog.adapter.interf.Tag> listFore(String contextPath);

    /**
     * 添加/修改标签
     *
     * @param tag tag
     */
    void saveTag(Tag tag);

    /**
     * 删除标签
     *
     * @param id tagId
     */
    void deleteTag(Integer id);

    /**
     * 根据标签id获取标签
     *
     * @param id tagId
     * @return
     */
    Tag getTagById(Integer id);

    /**
     * 根据标签id获取前台标签
     *
     * @param tagId 标签id
     * @param contextPath 上下文
     * @return 前台tag
     */
    cn.quitomos.blog.adapter.interf.Tag getForeTagById(Integer tagId, String contextPath);
}
