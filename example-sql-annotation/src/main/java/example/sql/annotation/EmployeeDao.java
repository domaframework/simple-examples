package example.sql.annotation;

import example.common.domain.Age;
import example.common.domain.Salary;
import example.common.entity.Department;
import example.common.entity.Employee;
import java.sql.Timestamp;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;
import org.seasar.doma.AggregateStrategy;
import org.seasar.doma.AssociationLinker;
import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.SelectType;
import org.seasar.doma.Sql;
import org.seasar.doma.Update;
import org.seasar.doma.jdbc.SelectOptions;

@Dao
public interface EmployeeDao {

  @Sql(
      """
			SELECT /*%expand*/*
			  FROM employee
			 WHERE id = /* id */0
			""")
  @Select
  Employee selectById(Integer id);

  @Sql(
      """
			SELECT /*%expand*/*
			  FROM employee
			 WHERE
			       /*%if min != null */
			       age >= /* min */10
			       /*%end */
			   /*%if max != null */
			   AND age <= /* max */70
			   /*%end */
			 ORDER BY age
			""")
  @Select
  List<Employee> selectByAgeRange(Age min, Age max);

  @Sql(
      """
			SELECT /*%expand*/*
			  FROM employee
			 WHERE
			       /*%for age : ages */
			       age = /* age */30
			         /*%if age_has_next */
			         /*# "or" */
			         /*%end */
			       /*%end */
			""")
  @Select
  List<Employee> selectByAges(List<Age> ages);

  @Sql(
      """
			SELECT /*%expand*/*
			  FROM employee
			 WHERE
			       /*%if name != null*/
			       name = /* name */'hoge'
			       /*%else */
			       AND name IS NULL
			       /*%end */
			""")
  @Select
  List<Employee> selectByName(String name);

  @Sql(
      """
			SELECT /*%expand*/*
			  FROM employee
			 WHERE name IN /* names */('aaa', 'bbb')
			""")
  @Select
  List<Employee> selectByNames(List<String> names);

  @Sql(
      """
			SELECT /*%expand*/*
			  FROM employee
			 WHERE
			       /*%if @isNotEmpty(name) */
			       name = /* name */'hoge'
			       /*%end*/
			""")
  @Select
  List<Employee> selectByNotEmptyName(String name);

  @Sql(
      """
			SELECT /*%expand*/*
			  FROM employee
			 WHERE name LIKE /* @prefix(prefix) */'X%' escape '$'
			""")
  @Select
  List<Employee> selectByNameWithPrefixMatching(String prefix);

  @Sql(
      """
			SELECT /*%expand*/*
			  FROM employee
			 WHERE name LIKE /* @suffix(suffix) */'%X' escape '$'
			""")
  @Select
  List<Employee> selectByNameWithSuffixMatching(String suffix);

  @Sql(
      """
			SELECT /*%expand*/*
			  FROM employee
			 WHERE name LIKE /* @infix(inside) */'%X%' escape '$'
			""")
  @Select
  List<Employee> selectByNameWithInfixMatching(String inside);

  @Sql(
      """
			SELECT /*%expand*/*
			  FROM employee
			 WHERE hiredate >= /* @roundDownTimePart(from) */'2001-01-01 12:34:56'
			   AND hiredate < /* @roundUpTimePart(to) */'2001-01-01 12:34:56'
			""")
  @Select
  List<Employee> selectByHiredateRange(Timestamp from, Timestamp to);

  @Sql(
      """
			SELECT /*%expand*/*
			  FROM employee
			 WHERE salary > /* salary */0
			""")
  @Select
  List<Employee> selectBySalary(Salary salary);

  @Sql(
      """
			SELECT sum(salary)
			  FROM employee
			""")
  @Select
  Salary selectSummedSalary();

  @Sql(
      """
			SELECT /*%expand*/*
			  FROM employee
			 WHERE name = /* e.name */'aaa'
			""")
  @Select
  List<Employee> selectByExample(Employee e);

  @Sql(
      """
			SELECT /*%expand*/*
			  FROM employee
			 ORDER BY id
			""")
  @Select
  List<Employee> selectAll();

  @Sql(
      """
			SELECT /*%expand*/*
			  FROM employee
			 ORDER BY id
			""")
  @Select
  List<Employee> selectAll(SelectOptions options);

  @Sql(
      """
			SELECT /*%expand*/*
			  FROM employee
			 WHERE age > /* age */0
			 ORDER BY age
			""")
  @Select(strategy = SelectType.STREAM)
  <R> R selectByAge(int age, Function<Stream<Employee>, R> mapper);

  @Sql(
      """
			SELECT /*%expand */*
			  FROM employee e
			       LEFT OUTER JOIN department d
			                    ON e.department_id = d.id
			 ORDER BY e.id
			""")
  @Select(aggregateStrategy = EmployeeAggregateStrategy.class)
  List<Employee> selectAllEmployeeDepartment();

  @Sql(
      """
			INSERT INTO Employee
			            (ID
			             , NAME
			             , AGE
			             , DEPARTMENT_ID
			             , HIREDATE
			             , JOB_TYPE
			             , SALARY
			             , INSERT_TIMESTAMP
			             , UPDATE_TIMESTAMP
			             , VERSION)
			     VALUES ( /* employee.id */1
			              , /* employee.name */'test'
			              , /* employee.age */10
			              , /* employee.departmentId */1
			              , /* employee.hiredate */'2010-01-01'
			              , /* employee.jobType */'SALESMAN'
			              , /* employee.salary */300
			              , /* employee.insertTimestamp */'2010-01-01 12:34:56'
			              , /* employee.updateTimestamp */'2010-01-01 12:34:56'
			              , /* employee.version */1 )
			""")
  @Insert
  int insert(Employee employee);

  @Sql(
      """
			UPDATE Employee
			   SET NAME = /* employee.name */'test'
			       , AGE = /* employee.age */10
			       , DEPARTMENT_ID = /* employee.departmentId */1
			       , HIREDATE = /* employee.hiredate */'2010-01-01'
			       , JOB_TYPE = /* employee.jobType */'SALESMAN'
			       , SALARY = /* employee.salary */300
			       , UPDATE_TIMESTAMP = /* employee.updateTimestamp */'2010-01-01 12:34:56'
			       , VERSION = /* employee.version */1
			 WHERE ID = /* employee.id */1
			""")
  @Update
  int update(Employee employee);

  @Sql(
      """
			DELETE FROM Employee
			 WHERE ID = /* employee.id */0
			""")
  @Delete
  int delete(Employee employee);
}

@AggregateStrategy(root = Employee.class, tableAlias = "e")
interface EmployeeAggregateStrategy {
  @AssociationLinker(propertyPath = "department", tableAlias = "d")
  BiFunction<Employee, Department, Employee> department =
      (e, d) -> {
        e.setDepartment(d);
        return e;
      };
}
