package cn.quitomos.blog.mapper;

import cn.quitomos.blog.entity.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagMapper {

    /**
     * 文章标签列表
     *
     * @return tagList
     */
    List<Tag> list();

    /**
     * 通过tagId获得文章标签
     *
     * @param id tagId
     * @return tag
     */
    Tag getTagById(int id);

    /**
     * 添加标签
     *
     * @param tag 添加的标签
     */
    void insertTag(Tag tag);

    /**
     * 根据tagId修改标签
     *
     * @param tag 修改的标签
     */
    void updateTag(Tag tag);

    /**
     * 根据tagId删除标签
     *
     * @param id categoryId
     */
    void deleteTagById(@Param("tag_id") int id);
}
