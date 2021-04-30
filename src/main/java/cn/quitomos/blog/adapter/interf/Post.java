package cn.quitomos.blog.adapter.interf;

import cn.quitomos.blog.entity.Article;

import java.util.Date;
import java.util.List;

public interface Post {

    String getFullPath();

    String getThumbnail();

    Date getCreateTime();

    String getTitle();

    int getVisits();

    int getCommentCount();

    List<Category> getCategories();

    String getSummary();

    int getId();

    boolean isDisallowComment();

    String getFormatContent();

    List<Tag> getTags();

    Article getSource();

    Date getUpdateTime();
}
