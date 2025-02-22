package example.geometric.type.dao;

import example.geometric.type.entity.Landmark;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Sql;

@Dao
public interface LandmarkDao {

  @Sql("select /*%expand */* from landmark where id = /* id */0")
  @Select
  Landmark selectById(Integer id);

  @Insert
  int insert(Landmark landmark);
}
