package demo.smart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.Resource;

import org.seasar.doma.jdbc.SelectOptions;

import demo.smart.dao.ProductDao;
import demo.smart.entity.Product;

public class ProductService {

    @Resource
    protected ProductDao productDao;

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