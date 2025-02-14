package example.dao_style_basic.dao;

import example.common.entity.Employee;
import java.util.List;
import org.seasar.doma.BatchDelete;
import org.seasar.doma.BatchInsert;
import org.seasar.doma.BatchUpdate;
import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;

@Dao
public interface EmployeeDao {

  @Select
  Employee selectById(Integer id);

  @Select
  List<Employee> selectAll();

  @Insert
  int insert(Employee employee);

  @Update
  int update(Employee employee);

  @Delete
  int delete(Employee employee);

  @BatchInsert
  int[] batchInsert(List<Employee> employees);

  @BatchUpdate
  int[] batchUpdate(List<Employee> employees);

  @BatchDelete
  int[] batchDelete(List<Employee> employees);
}
