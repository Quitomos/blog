package cn.quitomos.blog.service.impl;

import cn.quitomos.blog.adapter.PostAdapter;
import cn.quitomos.blog.adapter.PostsAdapter;
import cn.quitomos.blog.adapter.UserAdapter;
import cn.quitomos.blog.adapter.interf.Category;
import cn.quitomos.blog.adapter.interf.Post;
import cn.quitomos.blog.adapter.interf.Tag;
import cn.quitomos.blog.dto.ArticleDTO;
import cn.quitomos.blog.entity.Article;
import cn.quitomos.blog.entity.User;
import cn.quitomos.blog.enums.ArticleStatus;
import cn.quitomos.blog.mapper.ArticleCategoryRefMapper;
import cn.quitomos.blog.mapper.ArticleMapper;
import cn.quitomos.blog.mapper.ArticleTagRefMapper;
import cn.quitomos.blog.mapper.CommentMapper;
import cn.quitomos.blog.service.ArticleService;
import cn.quitomos.blog.util.DateUtil;
import cn.quitomos.blog.vo.Archive;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleMapper articleMapper;
    private ArticleTagRefMapper articleTagRefMapper;
    private ArticleCategoryRefMapper articleCategoryRefMapper;
    private CommentMapper commentMapper;

    @Override
    public List<Article> getRecentArticles(Integer num) {
        Map<String, Object> params = new HashMap<>();
        params.put("articleOrder", "article_create_time desc");
        PageHelper.startPage(1, num);
        return articleMapper.list(params);
    }

    @Override
    public List<Post> getRecentPosts(Integer num, String contextPath) {
        Map<String, Object> params = new HashMap<>();
        params.put("articleOrder", "article_create_time desc");
        PageHelper.startPage(1, num);
        List<Article> articleList = articleMapper.list(params);
        List<Post> posts = new ArrayList<>();
        for (Article a: articleList) {
            posts.add(new PostAdapter(a, contextPath));
        }
        return posts;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveArticle(ArticleDTO articleDTO, User user) {
        // 保存文章
        Article article = new Article();
        article.setArticleTitle(articleDTO.getArticleTitle());
        article.setArticleContent(articleDTO.getArticleContent());
        article.setArticleStatus(ArticleStatus.getArticleStatus(articleDTO.getArticleStatus()));
        article.setArticleId(articleDTO.getArticleId());
        article.setArticleImage(articleDTO.getArticleImage());
        article.setArticleSummary(articleDTO.getArticleSummary());
        article.setArticleUpdateTime(new Date());
        article.setUser(user);
        article.setArticleOrder(articleDTO.getArticleOrder());
        if (article.getArticleId() == null) {
            article.setArticleCreateTime(new Date());
            articleMapper.insertArticle(article);
        } else {
            articleMapper.updateArticle(article);
        }

        // 删除标签中间表项
        int articleId = article.getArticleId();
        articleTagRefMapper.deleteRefByArticleId(articleId);
        // 添加标签
        List<Integer> tagIdList = articleDTO.getArticleTagIds();
        for (int tagId: tagIdList) {
            articleTagRefMapper.insertRef(articleId, tagId);
        }

        // 删除分类中间表项
        articleCategoryRefMapper.deleteRefByArticleId(articleId);
        // 添加分类
        if (articleDTO.getArticleChildCategoryId() != null)
            articleCategoryRefMapper.insertRef(articleId, articleDTO.getArticleChildCategoryId());
        if (articleDTO.getArticleParentCategoryId() != null)
            articleCategoryRefMapper.insertRef(articleId, articleDTO.getArticleParentCategoryId());
    }

    @Override
    public PageInfo<Article> listArticlePaged(Integer pageNum, Integer pageSize, Map<String, Object> params) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> articleList = articleMapper.list(params);
        return new PageInfo<>(articleList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteArticle(Integer articleId) {
        articleCategoryRefMapper.deleteRefByArticleId(articleId);
        articleTagRefMapper.deleteRefByArticleId(articleId);
        commentMapper.deleteCommentByArticleId(articleId);
        articleMapper.deleteArticleById(articleId);
    }

    @Override
    public Article getArticleById(Integer articleId) {
        return articleMapper.getArticleById(articleId);
    }

    @Override
    public cn.quitomos.blog.adapter.interf.PageInfo<Post, Article> getPostsByCategory(Category category, String contextPath, String uri, Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<Article> articleList = category.getSource().getArticleList();
        PageInfo<Article> pageInfo= new PageInfo<>(articleList);
        return new PostsAdapter(pageInfo, uri, contextPath);
    }

    @Override
    public cn.quitomos.blog.adapter.interf.PageInfo<Post, Article> getPostsByTag(Tag tag, String contextPath, String uri, Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<Article> articleList = tag.getSource().getArticleList();
        PageInfo<Article> pageInfo = new PageInfo<>(articleList);
        return new PostsAdapter(pageInfo, uri, contextPath);
    }

    @Override
    public Post getPostById(Integer id, String contextPath) {
        Article article = articleMapper.getArticleById(id);
        if (article == null) return null;
        Post post = new PostAdapter(article, contextPath);
        return post;
    }

    @Override
    public cn.quitomos.blog.adapter.interf.User getUserByPost(Post post, String contextPath) {
        User userEntity = post.getSource().getUser();
        return new UserAdapter(userEntity, contextPath);
    }

    @Override
    public Post getPrevious(Post post, String contextPath) {
        Article article = articleMapper.getNext(post.getCreateTime());
        if (article == null) return null;
        return new PostAdapter(article, contextPath);
    }

    @Override
    public Post getNext(Post post, String contextPath) {
        Article article = articleMapper.getPrevious(post.getCreateTime());
        if (article == null) return null;
        return new PostAdapter(article, contextPath);
    }

    @Override
    public List<Archive> getArchives(String contextPath) {
        List<Article> articleList = articleMapper.list(null);
        List<Archive> archives = new ArrayList<>();
        for (Article article: articleList) {
            Post post = new PostAdapter(article, contextPath);
            Date createTime = article.getArticleCreateTime();
            int year = DateUtil.getYear(createTime);
            int month = DateUtil.getMonth(createTime);
            if (archives.isEmpty() ||archives.get(archives.size() - 1).getYear() != year || archives.get(archives.size() - 1).getMonth() != month) {
                Archive archive = new Archive();
                archive.setMonth(month);
                archive.setYear(year);
                archives.add(archive);
            }
            archives.get((archives.size() - 1)).getPosts().add(post);
        }
        return archives;
    }

    @Override
    public PostsAdapter getPostsByKeyword(String contextPath, String uri, String keyword, Integer pageNum) {
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword);
        PageHelper.startPage(pageNum, 5);
        List<Article> articleList = articleMapper.list(params);
        PageInfo<Article> pageInfo = new PageInfo<>(articleList);
        return new PostsAdapter(pageInfo, uri, contextPath);
    }

    @Override
    public void addViewCountByOne(int id) {
        articleMapper.addViewCount(id, 1);
    }

    @Autowired
    public void setArticleMapper(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Autowired
    public void setArticleTagRefMapper(ArticleTagRefMapper articleTagRefMapper) {
        this.articleTagRefMapper = articleTagRefMapper;
    }

    @Autowired
    public void setArticleCategoryRefMapper(ArticleCategoryRefMapper articleCategoryRefMapper) {
        this.articleCategoryRefMapper = articleCategoryRefMapper;
    }

    @Autowired
    public void setCommentMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }
}
