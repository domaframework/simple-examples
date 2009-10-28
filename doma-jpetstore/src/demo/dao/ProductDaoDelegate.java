package demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.SelectOptions;

import demo.entity.Product;

public class ProductDaoDelegate {

    private final Config config;

    public ProductDaoDelegate(Config config) {
        this.config = config;
    }

    public List<Product> searchProductList(List<String> keywords,
            SelectOptions options) {
        // TODO
        return new ArrayList<Product>();
    }
}
