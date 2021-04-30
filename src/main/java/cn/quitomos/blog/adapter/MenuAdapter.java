package cn.quitomos.blog.adapter;

import cn.quitomos.blog.adapter.interf.Menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuAdapter implements Menu, Serializable {

    private static final long serialVersionUID = -1820217394345931715L;
    private final cn.quitomos.blog.entity.Menu menu;

    public MenuAdapter(cn.quitomos.blog.entity.Menu menu) {
        this.menu = menu;
    }

    @Override
    public String getUrl() {
        return menu.getMenuUrl();
    }

    @Override
    public String getIcon() {
        return menu.getMenuIcon();
    }

    @Override
    public String getName() {
        return menu.getMenuName();
    }

    @Override
    public List<Menu> getChildren() {
        List<Menu> ret = new ArrayList<>();
        for (cn.quitomos.blog.entity.Menu m: menu.getChildList()) {
            ret.add(new MenuAdapter(m));
        }
        return ret;
    }
}
