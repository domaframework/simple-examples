package demo.smart.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.jdbc.SelectOptions;

import demo.config.AppConfig;
import demo.smart.entity.Product;

@Dao(config = AppConfig.class)
public interface ProductDao {

    @Select
    Product getProduct(String productId);

    @Select
    List<Product> getProductsByCategory(String categoryId, SelectOptions options);

    @Select
    List<Product> searchProductList(List<String> keywords, SelectOptions options);

}
