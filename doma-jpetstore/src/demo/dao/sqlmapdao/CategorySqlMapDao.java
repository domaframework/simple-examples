package demo.dao.sqlmapdao;

import com.ibatis.dao.client.DaoManager;

import demo.dao.CategoryDao;
import demo.entity.Category;

import java.util.List;

public class CategorySqlMapDao extends BaseSqlMapDao implements CategoryDao {

  public CategorySqlMapDao(DaoManager daoManager) {
    super(daoManager);
  }

  public List getCategoryList() {
    return queryForList("getCategoryList", null);
  }

  public Category getCategory(String categoryId) {
    return (Category) queryForObject("getCategory", categoryId);
  }

}
