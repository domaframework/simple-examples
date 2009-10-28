package demo.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Delegate;
import org.seasar.doma.Select;
import org.seasar.doma.jdbc.SelectOptions;

import demo.config.AppConfig;
import demo.entity.Product;

@Dao(config = AppConfig.class)
public interface ProductDao {

    @Select
    List<Product> getProductListByCategory(String categoryId,
            SelectOptions options);

    @Select
    Product getProduct(String productId);

    @Delegate(to = ProductDaoDelegate.class)
    List<Product> searchProductList(List<String> keywords, SelectOptions options);

}
