package cn.quitomos.blog.adapter.interf;

public interface Tag {

    String getName();

    int getPostCount();

    String getFullPath();

    String getThumbnail();

    String getDescription();

    cn.quitomos.blog.entity.Tag getSource();
}
