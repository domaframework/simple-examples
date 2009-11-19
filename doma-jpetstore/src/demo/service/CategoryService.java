package demo.service;

import demo.dao.CategoryDao;
import demo.dao.impl.CategoryDaoImpl;
import demo.entity.Category;

public class CategoryService {

    protected CategoryDao categoryDao = new CategoryDaoImpl();

    public Category getCategory(String categoryId) {
        return categoryDao.getCategory(categoryId);
    }

}