package cn.quitomos.blog.adapter.interf;

import java.util.List;

public interface Menu {

    String getUrl();

    String getIcon();

    String getName();

    List<Menu> getChildren();
}
