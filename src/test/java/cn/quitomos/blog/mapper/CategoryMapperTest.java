package cn.quitomos.blog.mapper;

import cn.quitomos.blog.BaseTest;
import cn.quitomos.blog.entity.Category;
import cn.quitomos.blog.service.CategoryService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryMapperTest extends BaseTest {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryService categoryService;

    @Test
    public void getCategory() {
//        List<Category> categoryList = categoryMapper.list();
        List<Category> categoryList = categoryService.list();
        for (Category c: categoryList) {
            System.out.println(c);
        }
    }
}
