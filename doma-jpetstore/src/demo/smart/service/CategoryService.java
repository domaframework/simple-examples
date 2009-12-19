package demo.smart.service;

import javax.annotation.Resource;

import demo.smart.dao.CategoryDao;
import demo.smart.entity.Category;

public class CategoryService {

    @Resource
    protected CategoryDao categoryDao;

    public Category getCategory(String categoryId) {
        return categoryDao.getCategory(categoryId);
    }

}