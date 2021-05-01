package cn.quitomos.blog.adapter;

import cn.quitomos.blog.adapter.interf.Category;
import cn.quitomos.blog.adapter.interf.Post;
import cn.quitomos.blog.adapter.interf.Tag;
import cn.quitomos.blog.entity.Article;
import cn.quitomos.blog.util.HtmlUtil;
import cn.quitomos.blog.util.MarkdownUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostAdapter implements Post, Serializable {

    private static final long serialVersionUID = 6223084215669758145L;
    private final Article article;
    private final String contextPath;

    public PostAdapter(Article article, String contextPath) {
        this.article = article;
        this.contextPath = contextPath;
    }

    @Override
    public String getFullPath() {
        return contextPath + "/article/" + article.getArticleId();
    }

    @Override
    public String getThumbnail() {
        return article.getArticleImage();
    }

    @Override
    public Date getCreateTime() {
        return article.getArticleCreateTime();
    }

    @Override
    public String getTitle() {
        return article.getArticleTitle();
    }

    @Override
    public int getVisits() {
        return article.getArticleViewCount();
    }

    @Override
    public int getCommentCount() {
        return article.getArticleCommentCount();
    }

    @Override
    public List<Category> getCategories() {
        List<cn.quitomos.blog.entity.Category> categoryEntityList = article.getCategoryList();
        List<Category> ret = new ArrayList<>();
        for (cn.quitomos.blog.entity.Category c: categoryEntityList) {
            ret.add(new CategoryAdapter(c, contextPath));
        }
        return ret;
    }

    @Override
    public List<Tag> getTags() {
        List<cn.quitomos.blog.entity.Tag> tagEntityList = article.getTagList();
        List<Tag> ret = new ArrayList<>();
        for (cn.quitomos.blog.entity.Tag t: tagEntityList) {
            ret.add(new TagAdapter(t, contextPath));
        }
        return ret;
    }

    @Override
    public Article getSource() {
        return this.article;
    }

    @Override
    public Date getUpdateTime() {
        return article.getArticleUpdateTime();
    }

    @Override
    public String getSummary() {
        return article.getArticleSummary();
    }

    @Override
    public int getId() {
        return article.getArticleId();
    }

    @Override
    public boolean isDisallowComment() {
        return !article.isArticleIsComment();
    }

    @Override
    public String getFormatContent() {
        return MarkdownUtil.renderHtml(article.getArticleContent());
    }
}
