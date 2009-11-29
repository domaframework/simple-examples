package demo.smart.service;

import demo.smart.dao.CategoryDao;
import demo.smart.dao.impl.CategoryDaoImpl;
import demo.smart.entity.Category;

public class CategoryService {

    protected CategoryDao categoryDao = new CategoryDaoImpl();

    public Category getCategory(String categoryId) {
        return categoryDao.getCategory(categoryId);
    }

}