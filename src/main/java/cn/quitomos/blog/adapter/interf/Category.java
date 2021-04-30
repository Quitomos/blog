package cn.quitomos.blog.adapter.interf;

import java.util.List;

public interface Category {

    String getFullPath();

    String getName();

    int getPostCount();

    List<Category> getChildList();

    String getIcon();

    String getDescription();

    int getSlug();

    cn.quitomos.blog.entity.Category getSource();

    String getThumbnail();
}
