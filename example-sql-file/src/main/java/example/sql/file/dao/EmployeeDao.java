package example.sql.file.dao;

import example.common.domain.Age;
import example.common.domain.Salary;
import example.common.entity.Department;
import example.common.entity.Employee;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Stream;
import org.seasar.doma.AggregateStrategy;
import org.seasar.doma.AssociationLinker;
import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.SelectType;
import org.seasar.doma.Update;
import org.seasar.doma.jdbc.SelectOptions;

@Dao
public interface EmployeeDao {

  @Select
  Employee selectById(Integer id);

  @Select
  List<Employee> selectByAgeRange(Age min, Age max);

  @Select
  List<Employee> selectByAges(List<Age> ages);

  @Select
  List<Employee> selectByName(String name);

  @Select
  List<Employee> selectByNames(List<String> names);

  @Select
  List<Employee> selectByNotEmptyName(String name);

  @Select
  List<Employee> selectByNameWithPrefixMatching(String prefix);

  @Select
  List<Employee> selectByNameWithSuffixMatching(String suffix);

  @Select
  List<Employee> selectByNameWithInfixMatching(String inside);

  @Select
  List<Employee> selectByHiredateRange(LocalDateTime from, LocalDateTime to);

  @Select
  List<Employee> selectBySalary(Salary salary);

  @Select
  Salary selectSummedSalary();

  @Select
  List<Employee> selectByExample(Employee e);

  @Select
  List<Employee> selectAll();

  @Select
  List<Employee> selectAll(SelectOptions options);

  @Select(strategy = SelectType.STREAM)
  <R> R selectByAge(int age, Function<Stream<Employee>, R> mapper);

  @Select(aggregateStrategy = EmployeeAggregateStrategy.class)
  List<Employee> selectAllEmployeeDepartment();

  @Insert(sqlFile = true)
  int insert(Employee employee);

  @Update(sqlFile = true)
  int update(Employee employee);

  @Delete(sqlFile = true)
  int delete(Employee employee);
}

@AggregateStrategy(root = Employee.class, tableAlias = "e")
interface EmployeeAggregateStrategy {
  @AssociationLinker(propertyPath = "department", tableAlias = "d")
  BiConsumer<Employee, Department> department = Employee::setDepartment;
}
