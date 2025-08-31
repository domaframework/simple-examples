package example.geometric.type.dao;

import example.geometric.type.entity.CircleZone;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Sql;

@Dao
public interface CircleZoneDao {

  @Sql(
      """
          SELECT /*%expand */*
            FROM circle_zone
           WHERE id = /* id */0
          """)
  @Select
  CircleZone selectById(Integer id);

  @Insert
  int insert(CircleZone circleZone);
}
