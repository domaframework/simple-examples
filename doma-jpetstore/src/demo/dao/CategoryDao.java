package demo.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;

import demo.config.AppConfig;
import demo.entity.Category;

@Dao(config = AppConfig.class)
public interface CategoryDao {

    @Select
    List<Category> getCategoryList();

    @Select
    Category getCategory(String categoryId);

}
