package demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.seasar.doma.jdbc.SelectOptions;

import demo.dao.ProductDao;
import demo.dao.impl.ProductDaoImpl;
import demo.entity.Product;

public class ProductService {

    private ProductDao productDao = new ProductDaoImpl();

    public Product getProduct(String productId) {
        return productDao.getProduct(productId);
    }

    public List<Product> getProductListByCategory(String categoryId) {
        return productDao
                .getProductsByCategory(categoryId, SelectOptions.get());
    }

    public List<Product> searchProductList(String keywords) {
        List<String> keywordList = new ArrayList<String>();
        for (StringTokenizer tokenizer = new StringTokenizer(keywords
                .toLowerCase(), " ", false); tokenizer.hasMoreTokens();) {
            keywordList.add(tokenizer.nextToken());
        }
        return productDao.searchProductList(keywordList, SelectOptions.get());
    }

}