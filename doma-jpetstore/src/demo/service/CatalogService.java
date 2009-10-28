package demo.service;

import java.util.ArrayList;
import java.util.List;

import org.seasar.doma.jdbc.SelectOptions;

import demo.dao.CategoryDao;
import demo.dao.ItemDao;
import demo.dao.ProductDao;
import demo.dao.impl.CategoryDaoImpl;
import demo.dao.impl.ItemDaoImpl;
import demo.dao.impl.ProductDaoImpl;
import demo.entity.Category;
import demo.entity.Item;
import demo.entity.Product;

public class CatalogService {

    private CategoryDao categoryDao;
    private ItemDao itemDao;
    private ProductDao productDao;
    private ItemService itemService;

    public CatalogService() {
        categoryDao = new CategoryDaoImpl();
        productDao = new ProductDaoImpl();
        itemDao = new ItemDaoImpl();
        itemService = new ItemService();
    }

    public List getCategoryList() {
        return categoryDao.getCategoryList();
    }

    public Category getCategory(String categoryId) {
        return categoryDao.getCategory(categoryId);
    }

    public Product getProduct(String productId) {
        return productDao.getProduct(productId);
    }

    public List<Product> getProductListByCategory(String categoryId) {
        // TODO
        return productDao.getProductListByCategory(categoryId, SelectOptions
                .get());
    }

    public List<Product> searchProductList(String keywords) {
        // TODO
        return productDao.searchProductList(new ArrayList<String>(),
                SelectOptions.get());
    }

    public List<Item> getItemListByProduct(String productId) {
        return itemDao.getItemListByProduct(productId);
    }

    public Item getItem(String itemId) {
        return itemDao.getItem(itemId);
    }

    public boolean isItemInStock(String itemId) {
        return itemService.isItemInStock(itemId);
    }

}