package cn.quitomos.blog.service;

import cn.quitomos.blog.adapter.PostsAdapter;
import cn.quitomos.blog.adapter.interf.Category;
import cn.quitomos.blog.adapter.interf.Post;
import cn.quitomos.blog.adapter.interf.Tag;
import cn.quitomos.blog.dto.ArticleDTO;
import cn.quitomos.blog.entity.Article;
import cn.quitomos.blog.entity.User;
import cn.quitomos.blog.vo.Archive;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface ArticleService {

    /**
     * 获得最近发表的文章
     *
     * @param num 文章数量
     * @return 文章列表
     */
    List<Article> getRecentArticles(Integer num);

    /**
     * 获得最近发表的文章, 适配前台
     *
     * @param num 文章数量
     * @return 前台文章列表
     */
    List<Post> getRecentPosts(Integer num, String contextPath);

    /**
     * 保存新文章或旧文章
     *
     * @param articleDTO articleDTO
     * @param user 写文章的用户
     */
    void saveArticle(ArticleDTO articleDTO, User user);

    /**
     * 文章列表
     *
     * @param pageNum 页码
     * @param pageSize 每页行数
     * @param params 查询参数
     * @return 文章列表
     */
    PageInfo<Article> listArticlePaged(Integer pageNum, Integer pageSize, Map<String ,Object> params);

    /**
     * 删除文章
     *
     * @param articleId 文章id
     */
    void deleteArticle(Integer articleId);

    /**
     * 根据文章id获取文章
     *
     * @param articleId 文章id
     * @return article
     */
    Article getArticleById(Integer articleId);

    /**
     * 获取前台分类下的文章
     *
     * @param category 前台分类
     * @param contextPath 上下文
     * @param uri 请求uri
     * @param pageNum 页数
     * @return posts
     */
    cn.quitomos.blog.adapter.interf.PageInfo<Post, Article> getPostsByCategory(Category category, String contextPath, String uri, Integer pageNum);

    /**
     * 获取前台标签下的文章
     *
     * @param tag 前台标签
     * @param contextPath 上下文
     * @param uri 请求uri
     * @param pageNum 页数
     * @return posts
     */
    cn.quitomos.blog.adapter.interf.PageInfo<Post, Article> getPostsByTag(Tag tag, String contextPath, String uri, Integer pageNum);

    /**
     * 获取一篇前台文章
     *
     * @param contextPath 上下文
     * @param id 文章id
     * @return post
     */
    Post getPostById(Integer id, String contextPath);

    /**
     * 获取前台文章的作者
     *
     * @param contextPath 上下文
     * @param post 前台文章
     * @return 前台用户
     */
    cn.quitomos.blog.adapter.interf.User getUserByPost(Post post, String contextPath);

    /**
     * 获取前台文章的上一篇文章
     *
     * @param contextPath 上下文
     * @param post 当前文章
     * @return 上一篇文章
     */
    Post getPrevious(Post post, String contextPath);

    /**
     * 获取前台文章的下一篇文章
     *
     * @param contextPath 上下文
     * @param post 当前文章
     * @return 下一篇文章
     */
    Post getNext(Post post, String contextPath);

    /**
     * 归档
     *
     * @param contextPath 上下文
     * @return archives
     */
    List<Archive> getArchives(String contextPath);

    /**
     * 搜索文章
     *
     * @param contextPath 上下文
     * @param uri uri
     * @param keyword 关键字
     * @param pageNum 页数
     * @return posts
     */
    PostsAdapter getPostsByKeyword(String contextPath, String uri, String keyword, Integer pageNum);

    /**
     * 文章浏览数+1
     *
     * @param id articleId
     */
    void addViewCountByOne(int id);
}

