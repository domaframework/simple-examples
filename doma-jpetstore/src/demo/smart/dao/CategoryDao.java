package demo.smart.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;

import demo.config.AppConfig;
import demo.smart.entity.Category;

@Dao(config = AppConfig.class)
public interface CategoryDao {

    @Select
    Category getCategory(String categoryId);

}
