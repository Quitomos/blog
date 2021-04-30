package cn.quitomos.blog.adapter;

import cn.quitomos.blog.adapter.interf.Category;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter implements Category, Serializable {

    private static final long serialVersionUID = -8162374573409440918L;
    private final cn.quitomos.blog.entity.Category category;
    private final String contextPath;

    public CategoryAdapter(cn.quitomos.blog.entity.Category category, String contextPath) {
        this.category = category;
        this.contextPath = contextPath;
    }

    @Override
    public String getFullPath() {
        return contextPath + "/category/" + category.getCategoryId();
    }

    @Override
    public String getName() {
        return category.getCategoryName();
    }

    @Override
    public int getPostCount() {
        return category.getArticleCount();
    }

    @Override
    public List<Category> getChildList() {
        List<Category> ret = new ArrayList<>();
        for (cn.quitomos.blog.entity.Category c: category.getChildList()) {
            ret.add(new CategoryAdapter(c, contextPath));
        }
        return ret;
    }

    @Override
    public String getIcon() {
        return category.getCategoryIcon();
    }

    @Override
    public String getDescription() {
        return category.getCategoryDescription();
    }

    @Override
    public int getSlug() {
        return category.getCategoryId();
    }

    @Override
    public cn.quitomos.blog.entity.Category getSource() {
        return this.category;
    }

    @Override
    public String getThumbnail() {
        return contextPath + "/img/category_patternimg.jpg";
    }
}
