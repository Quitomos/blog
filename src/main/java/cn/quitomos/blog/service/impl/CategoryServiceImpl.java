package cn.quitomos.blog.service.impl;

import cn.quitomos.blog.adapter.CategoryAdapter;
import cn.quitomos.blog.dto.CategoryDTO;
import cn.quitomos.blog.entity.Category;
import cn.quitomos.blog.mapper.CategoryMapper;
import cn.quitomos.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryMapper categoryMapper;

    @Override
    public List<Category> list() {
        return categoryMapper.list(null);
    }

    @Override
    public List<cn.quitomos.blog.adapter.interf.Category> listRoot(String contextPath) {
        Map<String, Object> params = new HashMap<>();
        params.put("categoryPid", 0);
        List<Category> categoryEntityList = categoryMapper.list(params);
        List<cn.quitomos.blog.adapter.interf.Category> ret = new ArrayList<>();
        for (Category c: categoryEntityList) {
            ret.add(new CategoryAdapter(c, contextPath));
        }
        return ret;
    }

    @Override
    public void saveCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryIcon(categoryDTO.getCategoryIcon());
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setCategoryOrder(categoryDTO.getCategoryOrder());
        category.setCategoryDescription(categoryDTO.getCategoryDescription());
        category.setParentCategory(categoryMapper.getCategoryById(categoryDTO.getCategoryPid()));
        Integer categoryId = categoryDTO.getCategoryId();
        if (categoryId == null) {
            categoryMapper.insertCategory(category);
        } else {
            category.setCategoryId(categoryId);
            categoryMapper.updateCategory(category);
        }
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        // 分类下无文章才能删除
        if (categoryMapper.getCategoryById(categoryId).getArticleCount() == 0)
            categoryMapper.deleteCategoryById(categoryId);
    }

    @Override
    public Category getCategoryById(Integer categoryId) {
        return categoryMapper.getCategoryById(categoryId);
    }

    @Override
    public cn.quitomos.blog.adapter.interf.Category getForeCategoryById(Integer categoryId, String contextPath) {
        return new CategoryAdapter(categoryMapper.getCategoryById(categoryId), contextPath);
    }

    @Autowired
    public void setCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }
}
