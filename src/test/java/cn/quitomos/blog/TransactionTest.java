package cn.quitomos.blog;

import cn.quitomos.blog.mapper.ArticleCategoryRefMapper;
import cn.quitomos.blog.mapper.ArticleMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class TransactionTest extends BaseTest {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleCategoryRefMapper articleCategoryRefMapper;

    @Test
    public void delete() {
//        articleCategoryRefMapper.insertRef(1,2);
//        articleMapper.deleteArticleById(1);
    }
}
