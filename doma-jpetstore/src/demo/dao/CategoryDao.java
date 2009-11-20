package demo.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;

import demo.cool.config.AppConfig;
import demo.entity.Category;

@Dao(config = AppConfig.class)
public interface CategoryDao {

    @Select
    Category getCategory(String categoryId);

}
