package example.jpms.dao;

import example.jpms.entity.Employee;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.Sql;

@Dao
public interface EmployeeDao {

  @Sql(
      """
      select
        /*%expand*/*
      from
        employee
      where
        id = /* id */0
      """)
  @Select
  Employee selectById(Integer id);
}
